package com.walker.aistock.domain.ai.service;

import com.walker.aistock.domain.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.domain.ai.vo.MessageVO;
import com.walker.aistock.domain.common.enums.AskModel;
import com.walker.aistock.domain.common.enums.AskRole;
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
import static java.time.LocalDate.now;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTService {

    private final FinvizService finvizService;
    private final FinnhubService finnhubService;
    private final FearGreedService fearGreedService;

    private final PromptService promptService;

    private final FileService fileService;
    private final WebClientService webClientService;

    public String chatGPTAnalysis(String keyword, String ticker) {

        if(ticker == null || ticker.equals("")) {
            ticker = findTicker(keyword);
        }

        // DATA
        FearGreedRes fearGreedRes = fearGreedService.fearGreed();
        FinvizDetailRes finvizDetailRes = finvizService.scrapingFinviz(ticker);
        List<StockRecommendRes> stockRecommendRes = finnhubService.stockRecommend(new StockRecommendReq(ticker));
        // TODO news에 대한 데이터는 좀 더 세부적인 내용이 있는 Source 찾아서 대체 요망
        List<StockNewsRes> stockNewsRes = finnhubService.stockNews(new StockNewsReq(ticker, now().minusDays(2).toString(), now().toString()));

        // AI
        String stockReport = webClientService.chatGPTAsk(promptService.makePromptForStockReport(ticker, fearGreedRes, finvizDetailRes, stockRecommendRes));
        String newsBriefing = webClientService.chatGPTAsk(promptService.makePromptForNewsTranslate(stockNewsRes));
        String imageId = chatGPTImage(promptService.makePromptStockImageDraw(newsBriefing));
        String speechId = chatGPTSpeech(new ChatGPTSpeechReq(stockReport + newsBriefing));

        // TODO 위에 Data들 DB에 저장 및 관리(image/speech 파일의 저장 경로 수정 요망)

        return stockReport + newsBriefing;
    }

    public String findTicker(String keyword) {
        return webClientService.chatGPTAsk(new ChatGPTAskReq(AskModel.GPT3_TURBO, // 단순 질문은 가벼운 model 사용
                                               List.of(new MessageVO(AskRole.USER, String.format(TICKER.getValue(), keyword))))
        );
    }

    public String chatGPTImage(ChatGPTImageReq chatGPTImageReq) {
        return fileService.saveBase64Image(webClientService.chatGPTImage(chatGPTImageReq).getData().getFirst().getBase64());
    }

    public String chatGPTSpeech(ChatGPTSpeechReq chatGPTSpeechReq) {
        return fileService.saveSpeechAudio(webClientService.chatGPTSpeech(chatGPTSpeechReq));
    }

    public String chatGPTText(ChatGPTAskReq chatGPTAskReq) {
        return webClientService.chatGPTAsk(chatGPTAskReq);
    }

}