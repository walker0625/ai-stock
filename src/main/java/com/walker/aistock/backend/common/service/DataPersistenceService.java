package com.walker.aistock.backend.common.service;

import com.walker.aistock.backend.ai.dto.res.ChatGPTImageRes;
import com.walker.aistock.backend.ai.entity.NewsBriefing;
import com.walker.aistock.backend.ai.entity.Report;
import com.walker.aistock.backend.ai.entity.Speech;
import com.walker.aistock.backend.ai.entity.TodayImage;
import com.walker.aistock.backend.ai.repository.NewsBriefingRepository;
import com.walker.aistock.backend.ai.repository.ReportRepository;
import com.walker.aistock.backend.ai.repository.SpeechRepository;
import com.walker.aistock.backend.ai.repository.TodayImageRepository;
import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.data.dto.res.FinvizDetailRes;
import com.walker.aistock.backend.data.dto.res.StockNewsRes;
import com.walker.aistock.backend.data.dto.res.StockRecommendRes;
import com.walker.aistock.backend.data.entity.Indicator;
import com.walker.aistock.backend.data.entity.News;
import com.walker.aistock.backend.data.entity.Recommend;
import com.walker.aistock.backend.data.repository.IndicatorRepository;
import com.walker.aistock.backend.data.repository.NewsRepository;
import com.walker.aistock.backend.data.repository.RecommendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataPersistenceService {

    FileService fileService;

    IndicatorRepository indicatorRepository;
    RecommendRepository recommendRepository;
    NewsRepository newsRepository;

    ReportRepository reportRepository;
    NewsBriefingRepository newsBriefingRepository;
    SpeechRepository speechRepository;
    TodayImageRepository todayImageRepository;

    @Transactional(rollbackFor = Exception.class)
    public void saveSourceDatas(FinvizDetailRes finvizDetailRes, StockRecommendRes stockRecommendRes, List<StockNewsRes> stockNewsRes, Stock stock) {

        indicatorRepository.save(Indicator.create(finvizDetailRes, stock));
        recommendRepository.save(Recommend.create(stockRecommendRes, stock));
        newsRepository.saveAll(stockNewsRes.stream().map(n -> News.create(n, stock)).collect(Collectors.toList()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveGeneratedTexts(String stockReport, String newsBriefing, Stock stock) {

        reportRepository.save(Report.create(stockReport, stock));
        newsBriefingRepository.save(NewsBriefing.create(newsBriefing, stock));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveGeneratedFiles(ChatGPTImageRes chatGPTImageRes, byte[] speechBinary, Stock stock) {

        String imageFileKey = UUID.randomUUID().toString();
        fileService.saveBase64Image(imageFileKey, chatGPTImageRes.getData().getFirst().getBase64());
        todayImageRepository.save(TodayImage.create(imageFileKey, stock));

        String speechFileKey = UUID.randomUUID().toString();
        fileService.saveSpeechAudio(speechFileKey, speechBinary);
        speechRepository.save(Speech.create(speechFileKey, stock));
    }

}