package com.walker.aistock.domain.ai.service;

import com.walker.aistock.domain.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.domain.ai.dto.res.ChatGPTImageRes;
import com.walker.aistock.domain.ai.vo.MessageVO;
import com.walker.aistock.domain.common.entity.Stock;
import com.walker.aistock.domain.common.enums.AskModel;
import com.walker.aistock.domain.common.enums.AskRole;
import com.walker.aistock.domain.common.repository.StockRepository;
import com.walker.aistock.domain.common.service.DataPersistenceService;
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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.walker.aistock.domain.common.enums.Prompt.*;
import static java.time.LocalDate.now;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatGPTService {

    FinvizService finvizService;
    FinnhubService finnhubService;
    FearGreedService fearGreedService;

    PromptService promptService;

    WebClientService webClientService;
    DataPersistenceService dataPersistenceService;

    StockRepository stockRepository;

    @Transactional
    public String chatGPTAnalysis(String keyword, String ticker) {

        if (ticker == null || ticker.equals("")) {
            ticker = findTicker(keyword);
        }

        Stock stock = stockRepository.findByTicker(ticker);

        if (stock == null) {
            stock = stockRepository.save(new Stock(ticker));
        }

        // DATA
        FinvizDetailRes finvizDetailRes = finvizService.scrapingFinviz(ticker);

        StockRecommendRes stockRecommendRes = finnhubService.stockRecommend(new StockRecommendReq(ticker));
        // TODO news에 대한 데이터는 좀 더 세부적인 내용이 있는 Source 찾아서 대체 요망
        List<StockNewsRes> stockNewsRes = finnhubService.stockNews(new StockNewsReq(ticker, now().minusDays(2).toString(), now().toString()));

        dataPersistenceService.saveSourceDatas(finvizDetailRes, stockRecommendRes, stockNewsRes, stock);

        // AI
        String stockReport = webClientService.chatGPTAsk(promptService.makePromptForStockReport(ticker, finvizDetailRes, stockRecommendRes));
        String newsBriefing = webClientService.chatGPTAsk(promptService.makePromptForNewsTranslate(stockNewsRes));

        dataPersistenceService.saveGeneratedTexts(stockReport, newsBriefing, stock);

        ChatGPTImageRes chatGPTImageRes = chatGPTImage(promptService.makePromptStockImageDraw(newsBriefing));
        byte[] speechBinary = chatGPTSpeech(new ChatGPTSpeechReq(stockReport + newsBriefing));

        dataPersistenceService.saveGeneratedFiles(chatGPTImageRes, speechBinary, stock);

        return stockReport + newsBriefing;
    }

    public String findTicker(String keyword) {
        return webClientService.chatGPTAsk(new ChatGPTAskReq(AskModel.GPT4_MINI, // 단순 질문은 가벼운 model 사용
                                               List.of(new MessageVO(AskRole.USER, String.format(TICKER.getValue(), keyword))))
        );
    }

    public ChatGPTImageRes chatGPTImage(ChatGPTImageReq chatGPTImageReq) {
        return webClientService.chatGPTImage(chatGPTImageReq);
    }

    public byte[] chatGPTSpeech(ChatGPTSpeechReq chatGPTSpeechReq) {
        return webClientService.chatGPTSpeech(chatGPTSpeechReq);
    }

    public String chatGPTText(ChatGPTAskReq chatGPTAskReq) {
        return webClientService.chatGPTAsk(chatGPTAskReq);
    }

}