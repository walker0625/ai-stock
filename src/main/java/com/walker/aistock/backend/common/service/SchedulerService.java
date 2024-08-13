package com.walker.aistock.backend.common.service;

import com.walker.aistock.backend.ai.service.ChatGPTService;
import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.repository.StockRepository;
import com.walker.aistock.backend.data.service.FearGreedService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@EnableScheduling
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SchedulerService {

    FearGreedService fearGreedService;
    ChatGPTService chatGPTService;

    StockRepository stockRepository;

    @Transactional
    @Scheduled(cron = "0 1 0 * * ?") // 매일 자정 +1분 - 해당 데이터가 없으면 main 화면 nullException
    public void makeFearGreed() {

        log.info("start makeFearGreed");
        fearGreedService.saveFearGreed();
        log.info("end makeFearGreed");

    }

    // TODO 1. DATA update 시점 2. 트래픽 한산한 시점 을 고려하여 시간 조정
    @Transactional
    @Scheduled(cron = "0 0 6 * * ?") // 매일 새벽 6시(예상 동작 시간 = 종목수 * 1분 30초(max))
    public void makeTodayStockData() {

        log.info("start makeTodayStockData");
        for (Stock stock : stockRepository.findAll()) {
            chatGPTService.chatGPTAnalysis(stock);
        }
        log.info("end makeTodayStockData");

    }

}
