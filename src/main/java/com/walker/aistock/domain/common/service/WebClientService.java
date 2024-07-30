package com.walker.aistock.domain.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walker.aistock.domain.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.domain.ai.dto.res.ChatGPTAskRes;
import com.walker.aistock.domain.ai.dto.res.ChatGPTImageRes;
import com.walker.aistock.domain.data.dto.req.StockNewsReq;
import com.walker.aistock.domain.data.dto.req.StockRecommendReq;
import com.walker.aistock.domain.data.dto.res.FearGreedRes;
import com.walker.aistock.domain.data.dto.res.StockNewsRes;
import com.walker.aistock.domain.data.dto.res.StockRecommendRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.walker.aistock.domain.common.enums.HeaderKey.*;
import static com.walker.aistock.domain.common.enums.HeaderKey.AUTHORIZATION;
import static com.walker.aistock.domain.common.enums.Url.*;
import static com.walker.aistock.domain.common.enums.Url.RAPID_FEARGREED;

@Slf4j
@Component
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

    public List<StockRecommendRes> stockRecommendation(StockRecommendReq stockRecommendReq) {
        return webClient.get()
                        .uri(stockRecommendReq.getStockRecommendUrlWithParam())
                        .header(FINNHUB.getValue(), FINNHUB_TOKEN)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<StockRecommendRes>>() {})
                        .block();
    }

    public ChatGPTAskRes chatGPTAsk(ChatGPTAskReq chatGPTAskReq) {

        String body;

        try {
            body = objectMapper.writeValueAsString(chatGPTAskReq);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return webClient.post()
                .uri(CHATGPT_ASK.getValue())
                .bodyValue(body)
                .header(AUTHORIZATION.getValue(), CHATGPT_TOKEN)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ChatGPTAskRes>() {})
                .block();
    }

    public ChatGPTImageRes chatGPTImage(ChatGPTImageReq chatGPTImageReq) {

        String body;

        try {
            body = objectMapper.writeValueAsString(chatGPTImageReq);
        } catch (JsonProcessingException e) {
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