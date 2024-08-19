package com.walker.aistock.backend.ai.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.walker.aistock.backend.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.backend.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.backend.ai.vo.MessageVO;
import com.walker.aistock.backend.ai.vo.QuantitativeDataVO;
import com.walker.aistock.backend.common.enums.AskRole;
import com.walker.aistock.backend.data.dto.res.FinvizDetailRes;
import com.walker.aistock.backend.data.dto.res.StockNewsRes;
import com.walker.aistock.backend.data.dto.res.StockRecommendRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.walker.aistock.backend.common.enums.Prompt.*;
import static com.walker.aistock.backend.common.enums.PromptTag.QUANTITATIVE_DATA;
import static com.walker.aistock.backend.common.enums.PromptTag.STOCK_NEWS;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromptService {

    ObjectMapper objectMapper;

    public ChatGPTAskReq makePromptForStockReport(String ticker, FinvizDetailRes finvizDetailRes, StockRecommendRes stockRecommendRes) {

        String quantitativeData = null;

        try {
            quantitativeData = objectMapper.writeValueAsString(new QuantitativeDataVO(ticker, finvizDetailRes, stockRecommendRes));
        } catch (JsonProcessingException e) {
            log.error("full error text", e);
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

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("image", "url");
        FilterProvider filters = new SimpleFilterProvider().addFilter("stockNewsFilter", filter);

        objectMapper.setFilterProvider(filters);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // null, 공백 제외

        String stockNewses = null;

        try {
            stockNewses = objectMapper.writeValueAsString(stockNewsRes);
        } catch (JsonProcessingException e) {
            log.error("full error text", e);
            throw new RuntimeException(e);
        }

        String newses =
            TEXT_GUIDE.getValue() +
            STOCK_NEWS.getStart() + stockNewses + STOCK_NEWS.getEnd() +
            NEWS_TRANSLATE.getValue() +
            NEWS_EXCEPT.getValue()
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