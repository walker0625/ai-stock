package com.walker.aistock.backend.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walker.aistock.backend.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.backend.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.backend.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.backend.ai.dto.res.ChatGPTAskRes;
import com.walker.aistock.backend.ai.dto.res.ChatGPTImageRes;
import com.walker.aistock.backend.data.dto.req.StockNewsReq;
import com.walker.aistock.backend.data.dto.req.StockRecommendReq;
import com.walker.aistock.backend.data.dto.res.FearGreedRes;
import com.walker.aistock.backend.data.dto.res.StockNewsRes;
import com.walker.aistock.backend.data.dto.res.StockRecommendRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.walker.aistock.backend.common.enums.HeaderKey.*;
import static com.walker.aistock.backend.common.enums.HeaderKey.AUTHORIZATION;
import static com.walker.aistock.backend.common.enums.Url.*;
import static com.walker.aistock.backend.common.enums.Url.RAPID_FEARGREED;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebClientService {

    @Value("${rapid.feargreed.token}")
    private String RAPID_FEARGREED_TOKEN;

    @Value("${finnhub.token}")
    private String FINNHUB_TOKEN;

    @Value("${chatgpt.token}")
    private String CHATGPT_TOKEN;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public FearGreedRes fearGreed() {
        return webClient.get()
                .uri(RAPID_FEARGREED.getValue())
                .header(RAPID.getValue(), RAPID_FEARGREED_TOKEN)
                .retrieve()
                .bodyToMono(FearGreedRes.class)
                .block();
    }

    public List<StockNewsRes> stockNews(StockNewsReq stockNewsReq) {
        return webClient.get()
                        .uri(stockNewsReq.getStockNewsUrlWithParam())
                        .header(FINNHUB.getValue(), FINNHUB_TOKEN)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<StockNewsRes>>() {})
                        .block();
    }

    public StockRecommendRes stockRecommendation(StockRecommendReq stockRecommendReq) {
        return webClient.get()
                        .uri(stockRecommendReq.getStockRecommendUrlWithParam())
                        .header(FINNHUB.getValue(), FINNHUB_TOKEN)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<StockRecommendRes>>() {})
                        .block()
                        .getFirst();
    }

    public String chatGPTAsk(ChatGPTAskReq chatGPTAskReq) {

        String body;

        try {
            body = objectMapper.writeValueAsString(chatGPTAskReq);
        } catch (JsonProcessingException e) {
            log.error("full error text", e);
            throw new RuntimeException(e);
        }

        return webClient.post()
                .uri(CHATGPT_ASK.getValue())
                .bodyValue(body)
                .header(AUTHORIZATION.getValue(), CHATGPT_TOKEN)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ChatGPTAskRes>() {})
                .block()
                // TODO 해당 부분이 계속 반복되어 공통화(응답은 1개라 문제가 없긴하나 WebClient 로직에 있는 것은 어색하여 리팩토링 요망)
                .getChoices().getFirst().getMessage().getContent();
    }

    public ChatGPTImageRes chatGPTImage(ChatGPTImageReq chatGPTImageReq) {

        String body;

        try {
            body = objectMapper.writeValueAsString(chatGPTImageReq);
        } catch (JsonProcessingException e) {
            log.error("full error text", e);
            throw new RuntimeException(e);
        }

        return webClient.post()
                .uri(CHATGPT_IMAGE.getValue())
                .bodyValue(body)
                .header(AUTHORIZATION.getValue(), CHATGPT_TOKEN)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ChatGPTImageRes>() {})
                .block();
    }

    public byte[] chatGPTSpeech(ChatGPTSpeechReq chatGPTSpeechReq) {

        String body;

        try {
            body = objectMapper.writeValueAsString(chatGPTSpeechReq);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return webClient.post()
                .uri(CHATGPT_SPEECH.getValue())
                .bodyValue(body)
                .header(AUTHORIZATION.getValue(), CHATGPT_TOKEN)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
    }

}