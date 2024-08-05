package com.walker.aistock.domain.common.service;

import com.walker.aistock.domain.ai.dto.res.ChatGPTImageRes;
import com.walker.aistock.domain.ai.entity.NewsBriefing;
import com.walker.aistock.domain.ai.entity.Report;
import com.walker.aistock.domain.ai.entity.Speech;
import com.walker.aistock.domain.ai.entity.TodayImage;
import com.walker.aistock.domain.ai.repository.NewsBriefingRepository;
import com.walker.aistock.domain.ai.repository.ReportRepository;
import com.walker.aistock.domain.ai.repository.SpeechRepository;
import com.walker.aistock.domain.ai.repository.TodayImageRepository;
import com.walker.aistock.domain.common.entity.Stock;
import com.walker.aistock.domain.common.repository.StockRepository;
import com.walker.aistock.domain.data.dto.res.FearGreedRes;
import com.walker.aistock.domain.data.dto.res.FinvizDetailRes;
import com.walker.aistock.domain.data.dto.res.StockNewsRes;
import com.walker.aistock.domain.data.dto.res.StockRecommendRes;
import com.walker.aistock.domain.data.entity.FearGreed;
import com.walker.aistock.domain.data.entity.Indicator;
import com.walker.aistock.domain.data.entity.News;
import com.walker.aistock.domain.data.entity.Recommend;
import com.walker.aistock.domain.data.repository.IndicatorRepository;
import com.walker.aistock.domain.data.repository.FearGreedRepository;
import com.walker.aistock.domain.data.repository.NewsRepository;
import com.walker.aistock.domain.data.repository.RecommendRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataPersistenceService {

    FileService fileService;

    StockRepository stockRepository;

    FearGreedRepository fearGreedRepository;
    IndicatorRepository indicatorRepository;
    RecommendRepository recommendRepository;
    NewsRepository newsRepository;

    ReportRepository reportRepository;
    NewsBriefingRepository newsBriefingRepository;
    SpeechRepository speechRepository;
    TodayImageRepository todayImageRepository;

    @Transactional
    public void saveSourceDatas(FinvizDetailRes finvizDetailRes, StockRecommendRes stockRecommendRes, List<StockNewsRes> stockNewsRes, Stock stock) {

        indicatorRepository.save(Indicator.create(finvizDetailRes, stock));
        recommendUpsert(stockRecommendRes, stock);
        newsRepository.saveAll(stockNewsRes.stream().map(n -> News.create(n, stock)).collect(Collectors.toList()));
    }

    @Transactional
    public void recommendUpsert(StockRecommendRes stockRecommendRes, Stock stock) {

        Recommend recommend = recommendRepository.findByStockIdAndRecommendDate(stock.getId(), stockRecommendRes.getPeriod());

        if (recommend == null) {
            recommendRepository.save(Recommend.create(stockRecommendRes, stock));
        } else {
            recommend.modify(stockRecommendRes);
        }
    }

    @Transactional
    public void saveGeneratedTexts(String stockReport, String newsBriefing, Stock stock) {
        reportRepository.save(Report.create(stockReport, stock));
        newsBriefingRepository.save(NewsBriefing.create(newsBriefing, stock));
    }

    @Transactional
    public void saveGeneratedFiles(ChatGPTImageRes chatGPTImageRes, byte[] speechBinary, Stock stock) {

        String imageKey = UUID.randomUUID().toString();
        fileService.saveBase64Image(imageKey, chatGPTImageRes.getData().getFirst().getBase64());
        todayImageRepository.save(TodayImage.create(imageKey, stock));

        String speechKey = UUID.randomUUID().toString();
        fileService.saveSpeechAudio(speechKey, speechBinary);
        speechRepository.save(Speech.create(speechKey, stock));
    }

}