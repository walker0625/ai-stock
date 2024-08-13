package com.walker.aistock.backend.ai.service;

import com.walker.aistock.backend.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.backend.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.backend.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.backend.ai.dto.res.ChatGPTImageRes;
import com.walker.aistock.backend.ai.vo.MessageVO;
import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.enums.AskModel;
import com.walker.aistock.backend.common.enums.AskRole;
import com.walker.aistock.backend.common.repository.StockRepository;
import com.walker.aistock.backend.common.service.DataPersistenceService;
import com.walker.aistock.backend.common.service.WebClientService;
import com.walker.aistock.backend.data.dto.req.StockNewsReq;
import com.walker.aistock.backend.data.dto.req.StockRecommendReq;
import com.walker.aistock.backend.data.dto.res.FinvizDetailRes;
import com.walker.aistock.backend.data.dto.res.StockNewsRes;
import com.walker.aistock.backend.data.dto.res.StockRecommendRes;
import com.walker.aistock.backend.data.service.FinnhubService;
import com.walker.aistock.backend.data.service.FinvizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.walker.aistock.backend.common.enums.Prompt.*;
import static java.time.LocalDate.now;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatGPTService {

    FinvizService finvizService;
    FinnhubService finnhubService;

    PromptService promptService;

    WebClientService webClientService;
    DataPersistenceService dataPersistenceService;

    StockRepository stockRepository;

    public void addStock(String keyword) {
        String nameAndTicker = webClientService.chatGPTAsk(new ChatGPTAskReq(AskModel.GPT4_MINI, // 단순 질문은 가벼운 model 사용
                List.of(new MessageVO(AskRole.USER, String.format(TICKER.getValue(), keyword)))));

        stockRepository.save(new Stock(nameAndTicker.split(":")[0], nameAndTicker.split(":")[1]));
    }

    @Transactional
    public String chatGPTAnalysis(Stock stock) {

        String ticker = stock.getTicker();

        // DATA
        FinvizDetailRes finvizDetailRes = finvizService.scrapingFinviz(ticker);

        StockRecommendRes stockRecommendRes = finnhubService.stockRecommend(new StockRecommendReq(ticker));
        // TODO news에 대한 데이터는 좀 더 세부적인 내용이 있는 Source 찾아서 대체 요망
        List<StockNewsRes> stockNewsRes = finnhubService.stockNews(new StockNewsReq(ticker, now().minusDays(1).toString(), now().toString()));

        dataPersistenceService.saveSourceDatas(finvizDetailRes, stockRecommendRes, stockNewsRes, stock);

        // AI
        String stockReport = webClientService.chatGPTAsk(promptService.makePromptForStockReport(ticker, finvizDetailRes, stockRecommendRes));
        String newsBriefing = webClientService.chatGPTAsk(promptService.makePromptForNewsTranslate(stockNewsRes));

        dataPersistenceService.saveGeneratedTexts(stockReport, newsBriefing, stock);

        ChatGPTImageRes chatGPTImageRes = chatGPTImage(promptService.makePromptStockImageDraw(newsBriefing));
        // TODO 단순히 자르는게 아니라 내용 자체를 4000자 이내로 요약할 수 있게 변경
        String speechInput = (stockReport + newsBriefing).substring(0, 4000);
        log.info("speechInput: {}", speechInput);
        byte[] speechBinary = chatGPTSpeech(new ChatGPTSpeechReq(speechInput));

        dataPersistenceService.saveGeneratedFiles(chatGPTImageRes, speechBinary, stock);

        return stockReport + newsBriefing;
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