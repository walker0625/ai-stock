package com.walker.aistock.domain.ai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walker.aistock.domain.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.domain.ai.dto.res.ChatGPTAskRes;
import com.walker.aistock.domain.ai.dto.vo.MessageVO;
import com.walker.aistock.domain.ai.dto.vo.QuantitativeDataVO;
import com.walker.aistock.domain.ai.vo.ImageData;
import com.walker.aistock.domain.common.enums.AskModel;
import com.walker.aistock.domain.common.enums.AskRole;
import com.walker.aistock.domain.common.enums.Tag;
import com.walker.aistock.domain.common.service.FileService;
import com.walker.aistock.domain.common.service.WebClientService;
import com.walker.aistock.domain.data.dto.req.StockNewsReq;
import com.walker.aistock.domain.data.dto.req.StockRecommendReq;
import com.walker.aistock.domain.data.dto.res.FearGreedRes;
import com.walker.aistock.domain.data.dto.res.FinvizDetailRes;
import com.walker.aistock.domain.data.dto.res.StockNewsRes;
import com.walker.aistock.domain.data.dto.res.StockRecommendRes;
import com.walker.aistock.domain.data.service.FearGreedService;
import com.walker.aistock.domain.data.service.FinnhubService;
import com.walker.aistock.domain.data.service.FinvizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.walker.aistock.domain.common.enums.Prompt.*;
import static com.walker.aistock.domain.common.enums.Prompt.ANALYST;
import static com.walker.aistock.domain.common.enums.Prompt.STOCK_QUESTION;
import static com.walker.aistock.domain.common.enums.Tag.QUANTITATIVE_DATA;
import static com.walker.aistock.domain.common.enums.Tag.STOCK_NEWS;
import static java.time.LocalDate.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTService {

    private final FinvizService finvizService;
    private final FinnhubService finnhubService;
    private final FearGreedService fearGreedService;

    private final FileService fileService;
    private final WebClientService webClientService;

    private final ObjectMapper objectMapper;

    public String chatGPTReport(String keyword, String ticker) {

        if(ticker == null || ticker.equals("")) {
            ticker = chatGPTSearchByKeyword(keyword);
        }

        FearGreedRes fearGreedRes = fearGreedService.fearGreed();
        FinvizDetailRes finvizDetailRes = finvizService.scrapingFinviz(ticker);
        List<StockRecommendRes> stockRecommendRes = finnhubService.stockRecommend(new StockRecommendReq(ticker));
        // TODO news에 대한 데이터는 좀 더 세부적인 내용이 있는 Source 찾아서 대체 요망
        List<StockNewsRes> stockNewsRes = finnhubService.stockNews(new StockNewsReq(ticker, now().minusDays(2).toString(), now().toString()));

        // TODO Response들로 ask와 image용 프롬프트 생성하여 요청 후, ask 응답은 speech로 변환(각 단계의 data db 저장)
        String stockAnalysis = webClientService.chatGPTAsk(makePromptForStockAnalysis(ticker, fearGreedRes, finvizDetailRes, stockRecommendRes))
                                               .getChoices().getFirst().getMessage().getContent();

        String newses = webClientService.chatGPTAsk(makePromptForNewsTranslate(stockNewsRes))
                                        .getChoices().getFirst().getMessage().getContent();

        return stockAnalysis + newses;
    }

    public String chatGPTSearchByKeyword(String keyword) {

        ChatGPTAskRes chatGPTAskRes = webClientService.chatGPTAsk(
            new ChatGPTAskReq(AskModel.GPT3_TURBO, List.of(new MessageVO(AskRole.USER, String.format(TICKER.getValue(), keyword))))
        );

        return chatGPTAskRes.getChoices().get(0).getMessage().getContent();
    }

    private ChatGPTAskReq makePromptForStockAnalysis(String ticker, FearGreedRes fearGreedRes, FinvizDetailRes finvizDetailRes,
                                                     List<StockRecommendRes> stockRecommendRes) {

        String quantitativeData = null;

        try {
            quantitativeData = objectMapper.writeValueAsString(new QuantitativeDataVO(ticker, fearGreedRes, finvizDetailRes, stockRecommendRes));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String stockAsk =
                GUIDE.getValue() +
                QUANTITATIVE_DATA.getStart() + quantitativeData + QUANTITATIVE_DATA.getEnd() +
                STOCK_QUESTION.getValue()
                ;

        return new ChatGPTAskReq(List.of(new MessageVO(AskRole.SYSTEM, ANALYST.getValue()),
                                         new MessageVO(AskRole.USER, stockAsk)));
    }

    private ChatGPTAskReq makePromptForNewsTranslate(List<StockNewsRes> stockNewsRes) {

        String stockNewses = null;

        try {
            stockNewses = objectMapper.writeValueAsString(stockNewsRes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String newses =
                GUIDE.getValue() +
                STOCK_NEWS.getStart() + stockNewses + STOCK_NEWS.getEnd() +
                NEWS_TRANSLATE.getValue()
                ;

        return new ChatGPTAskReq(List.of(new MessageVO(AskRole.SYSTEM, TRANSLATOR.getValue()),
                                         new MessageVO(AskRole.USER, newses)));
    }

    public String chatGPTImage(ChatGPTImageReq chatGPTImageReq) {

        ImageData imageData = webClientService.chatGPTImage(chatGPTImageReq).getData().getFirst(); // 1장만 생성

        return fileService.saveBase64Image(imageData.getBase64());
    }

    public String chatGPTSpeech(ChatGPTSpeechReq chatGPTSpeechReq) {
        return fileService.saveSpeechAudio(webClientService.chatGPTSpeech(chatGPTSpeechReq));
    }

    public String chatGPTText(ChatGPTAskReq chatGPTAskReq) {
        return webClientService.chatGPTAsk(chatGPTAskReq).getChoices().get(0).getMessage().getContent();
    }
}