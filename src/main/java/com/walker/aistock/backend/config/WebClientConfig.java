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
					.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(5 * 1024 * 1024)) // 5MB(받아오는 이미지 크기(base64) 제한)
					.build()
				)
				.clientConnector(new ReactorClientHttpConnector(
						HttpClient.create().responseTimeout(Duration.ofMinutes(3))
				))
				.filter(errorHandler())
				.build();
	}

	/**
	 * WebClient Error Handling
	 */
	public ExchangeFilterFunction errorHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			if (clientResponse.statusCode().is5xxServerError()) {
				log.error("code :: {}", clientResponse.statusCode());
				throw new RuntimeException("server error");
			} else if (clientResponse.statusCode().is4xxClientError()) {
				throw new RuntimeException("client error");
			} else {
				return Mono.just(clientResponse);
			}
		});
	}

}