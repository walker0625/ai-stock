package com.walker.aistock.domain.data.controller;

import com.walker.aistock.domain.data.dto.req.StockNewsReq;
import com.walker.aistock.domain.data.dto.req.StockRecommendReq;
import com.walker.aistock.domain.data.service.FinnhubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/finnhub")
@RequiredArgsConstructor
public class FinnhubController {

    private final FinnhubService finnhubService;

    @GetMapping("/news")
    public String stockNews(StockNewsReq stockNewsReq) {
        return finnhubService.stockNews(stockNewsReq).toString();
    }

    @GetMapping("/recommend")
    public String stockRecommend(StockRecommendReq stockRecommendReq) {
        return finnhubService.stockRecommend(stockRecommendReq).toString();
    }

}
