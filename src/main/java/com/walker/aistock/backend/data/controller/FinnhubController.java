package com.walker.aistock.backend.data.controller;

import com.walker.aistock.backend.data.dto.req.StockNewsReq;
import com.walker.aistock.backend.data.dto.req.StockRecommendReq;
import com.walker.aistock.backend.data.service.FinnhubService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/finnhub")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FinnhubController {

    FinnhubService finnhubService;

    @GetMapping("/news")
    public String stockNews(StockNewsReq stockNewsReq) {
        return finnhubService.stockNews(stockNewsReq).toString();
    }

    @GetMapping("/recommend")
    public String stockRecommend(StockRecommendReq stockRecommendReq) {
        return finnhubService.stockRecommend(stockRecommendReq).toString();
    }

}
