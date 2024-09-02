package com.walker.aistock.front.service;

import com.walker.aistock.backend.common.repository.PrincipleRepository;
import com.walker.aistock.backend.common.repository.StockRepository;
import com.walker.aistock.backend.data.repository.FearGreedRepository;
import com.walker.aistock.backend.data.repository.NewsRepository;
import com.walker.aistock.front.dto.res.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PresentationService {

    FearGreedRepository fearGreedRepository;
    StockRepository stockRepository;
    NewsRepository newsRepository;
    PrincipleRepository principleRepository;

    public PresentationFearGreedRes fearGreed() {
        return new PresentationFearGreedRes(fearGreedRepository.findByCreatedAtToday());
    }

    public List<StockImageSpeechRes> stockWithImageAndSpeech() {
        return stockRepository.findStocksWithImagesAndSpeechesBetweenThreeDays(LocalDate.now().minusDays(2), LocalDate.now());
    }

    public StockDetailsRes stockWithDetails(Long stockId, LocalDate selectedDate) {
        LocalDateTime startDate = LocalDateTime.of(selectedDate, LocalTime.MIN);
        // LocalTime.MAX로 DB에 쿼리하면 다음날까지 조회되는 이슈 있음
        LocalDateTime endDate = LocalDateTime.of(selectedDate, LocalTime.of(23, 59, 59, 9999));

        return stockRepository.findStockWithDetails(stockId, startDate, endDate);
    }

    public List<PresentationNewsRes> stockWithNewses(Long stockId, LocalDate selectedDate) {
        return newsRepository.findByStockIdAndCreatedAt(stockId, selectedDate, PageRequest.of(0, 7))
                             .stream().map(PresentationNewsRes::new).collect(Collectors.toList());
    }

    //  TODO 추후 user별로 분리가 필요하면 1L에서 user id로 변경
    public PrincipleRes principle() {
        return principleRepository.findById(1L).map(p -> new PrincipleRes(p.getContent())).get();
    }

}