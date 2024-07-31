package com.walker.aistock.domain.data.service;

import com.walker.aistock.domain.common.service.WebClientService;
import com.walker.aistock.domain.data.dto.req.StockNewsReq;
import com.walker.aistock.domain.data.dto.req.StockRecommendReq;
import com.walker.aistock.domain.data.dto.res.StockNewsRes;
import com.walker.aistock.domain.data.dto.res.StockRecommendRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinnhubService {

    private final WebClientService webClientService;

    public List<StockNewsRes> stockNews(StockNewsReq stockNewsReq) {
        return webClientService.stockNews(stockNewsReq);
    }

    public List<StockRecommendRes> stockRecommend(StockRecommendReq stockRecommendReq) {
        return webClientService.stockRecommendation(stockRecommendReq);
    }

}