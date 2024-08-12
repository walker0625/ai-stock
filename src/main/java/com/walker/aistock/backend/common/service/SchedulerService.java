package com.walker.aistock.backend.common.service;

import com.walker.aistock.backend.ai.service.ChatGPTService;
import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.repository.StockRepository;
import com.walker.aistock.backend.data.service.FearGreedService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Collectors;


@Component
@EnableScheduling
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SchedulerService {

    FearGreedService fearGreedService;
    ChatGPTService chatGPTService;

    StockRepository stockRepository;

    @Transactional
    @Scheduled(cron = "0 57 14 * * ?")
    public void makeTodayStockData() {

        fearGreedService.saveFearGreed();

        for (Stock stock : stockRepository.findAll()) {
            chatGPTService.chatGPTAnalysis(stock);
        }

    }

}
