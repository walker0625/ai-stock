package com.walker.aistock.backend.common.service;

import java.time.LocalDate;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CacheService {

    @CacheEvict(
        value = "stockList",
        key = "'stock_list:date:' + #targetDate.format(T(java.time.format.DateTimeFormatter).ofPattern('yyyy-MM-dd'))"
    )
    public void deleteStockListOneDay(LocalDate targetDate) {
        log.info("deleteStockListOneDay:targetDate:{}", targetDate);
    }

}