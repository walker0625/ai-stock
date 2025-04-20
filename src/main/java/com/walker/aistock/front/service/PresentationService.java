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

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Cacheable(
        value = "fearGreed",
        key = "'date:' + T(java.time.LocalDate).now().format(T(java.time.format.DateTimeFormatter).ofPattern('yyyy-MM-dd'))"
    )
    public PresentationFearGreedRes fearGreed() {
        return new PresentationFearGreedRes(fearGreedRepository.findByCreatedAtToday());
    }

    @Cacheable(
        value = "stockList",
        key = "'stock_list:date:' + T(java.time.LocalDate).now().format(T(java.time.format.DateTimeFormatter).ofPattern('yyyy-MM-dd'))"
    )
    public List<StockImageRes> stockWithImage() {
        return stockRepository.findStocksWithImagesBetweenThreeDays(LocalDate.now().minusDays(2), LocalDate.now());
    }

    @Cacheable(
        value = "stockDetail",
        key = "'stock_detail:stock_id:' + #stockId + ':date:' + #date"
    )
    public StockDetailsRes stockWithDetails(String stockId, String date) {

        LocalDate selectedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDateTime startDate = LocalDateTime.of(selectedDate, LocalTime.MIN);
        // LocalTime.MAX로 DB에 쿼리하면 다음날까지 조회되는 이슈 있음
        LocalDateTime endDate = LocalDateTime.of(selectedDate, LocalTime.of(23, 59, 59, 9999));

        return stockRepository.findStockWithDetails(Long.parseLong(stockId), startDate, endDate);
    }

    @Cacheable(
        value = "stockNews",
        key = "'stock_news:stock_id:' + #stockId + ':date:' + #date"
    )
    public List<PresentationNewsRes> stockWithNewses(String stockId, String date) {

        LocalDate selectedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return newsRepository.findByStockIdAndCreatedAt(Long.parseLong(stockId), selectedDate, PageRequest.of(0, 7))
                             .stream().map(PresentationNewsRes::new).collect(Collectors.toList());
    }

    @Cacheable(
        value = "investingPrinciple", // TTL ZERO(principle)
        key = "'investing_principle'"
    )
    public PrincipleRes principle() {
        return principleRepository.findById(1L).map(p -> new PrincipleRes(p.getContent())).get(); // TODO 추후 user별로 분리가 필요하면 1L에서 user id로 변경
    }

}