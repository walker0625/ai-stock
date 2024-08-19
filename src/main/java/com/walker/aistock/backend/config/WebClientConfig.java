package com.walker.aistock.backend.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class WebClientConfig {

	@Bean
	public WebClient webClient() {

		/**
		 * Set Connection Timeout
		 */
		HttpClient httpClient = HttpClient.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				.responseTimeout(Duration.ofMillis(10000))
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS))
										   .addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS)));

		return WebClient.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.ALL_VALUE)
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.exchangeStrategies(
					ExchangeStrategies.builder()
					.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10MB(받아오는 이미지/오디오 파일 제한)
					.build()
				)
				.clientConnector(new ReactorClientHttpConnector(
						HttpClient.create().responseTimeout(Duration.ofMinutes(3)) // 요청에 대한 응답 대기시간 3분 제한
				))
				.filter(errorHandler())
				.build();
	}

	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		ServerCodecConfigurer configurer = ServerCodecConfigurer.create();
		configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024); // 요청/응답의 버퍼 10MB로 설정(DataBufferLimitException)
		return configurer;
	}

	/**
	 * WebClient Error Handling
	 */
	public ExchangeFilterFunction errorHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			if (clientResponse.statusCode().isError()) {
				return clientResponse.bodyToMono(String.class)
						.flatMap(body -> {
							log.error("Status Code: {}", clientResponse.statusCode());
							log.error("Response Body: {}", body);

							if (clientResponse.statusCode().is5xxServerError()) {
								return Mono.error(new RuntimeException("Server error: " + body));
							} else if (clientResponse.statusCode().is4xxClientError()) {
								return Mono.error(new RuntimeException("Client error: " + body));
							} else {
								return Mono.just(clientResponse);
							}
						});
			} else {
				return Mono.just(clientResponse);
			}
		});
	}

}