package com.walker.aistock.domain.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walker.aistock.domain.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.domain.ai.dto.vo.MessageVO;
import com.walker.aistock.domain.ai.dto.vo.QuantitativeDataVO;
import com.walker.aistock.domain.common.enums.AskRole;
import com.walker.aistock.domain.data.dto.res.FearGreedRes;
import com.walker.aistock.domain.data.dto.res.FinvizDetailRes;
import com.walker.aistock.domain.data.dto.res.StockNewsRes;
import com.walker.aistock.domain.data.dto.res.StockRecommendRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.walker.aistock.domain.common.enums.Prompt.*;
import static com.walker.aistock.domain.common.enums.Tag.QUANTITATIVE_DATA;
import static com.walker.aistock.domain.common.enums.Tag.STOCK_NEWS;

@Service
@RequiredArgsConstructor
public class PromptService {

    public final ObjectMapper objectMapper;

    public ChatGPTAskReq makePromptForStockAnalysis(String ticker, FearGreedRes fearGreedRes, FinvizDetailRes finvizDetailRes,
                                                    List<StockRecommendRes> stockRecommendRes) {

        String quantitativeData = null;

        try {
            quantitativeData = objectMapper.writeValueAsString(new QuantitativeDataVO(ticker, fearGreedRes, finvizDetailRes, stockRecommendRes));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String stockAsk =
            TEXT_GUIDE.getValue() +
            QUANTITATIVE_DATA.getStart() + quantitativeData + QUANTITATIVE_DATA.getEnd() +
            STOCK_QUESTION.getValue()
        ;

        return new ChatGPTAskReq(List.of(new MessageVO(AskRole.SYSTEM, ANALYST.getValue()),
                new MessageVO(AskRole.USER, stockAsk)));
    }

    public ChatGPTAskReq makePromptForNewsTranslate(List<StockNewsRes> stockNewsRes) {

        String stockNewses = null;

        try {
            stockNewses = objectMapper.writeValueAsString(stockNewsRes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String newses =
            TEXT_GUIDE.getValue() +
            STOCK_NEWS.getStart() + stockNewses + STOCK_NEWS.getEnd() +
            NEWS_TRANSLATE.getValue()
        ;

        return new ChatGPTAskReq(List.of(new MessageVO(AskRole.SYSTEM, TRANSLATOR.getValue()),
                new MessageVO(AskRole.USER, newses)));
    }

    public ChatGPTImageReq makePromptStockImageDraw(String newses) {

        String imagePrompt =
            ARTIST.getValue() +
            STOCK_NEWS.getStart() + newses + STOCK_NEWS.getEnd() +
            IMAGE_DRAW.getValue()
        ;

        return new ChatGPTImageReq(imagePrompt);
    }

}