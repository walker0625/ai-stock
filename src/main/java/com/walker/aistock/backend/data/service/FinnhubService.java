package com.walker.aistock.backend.data.service;

import com.walker.aistock.backend.common.service.WebClientService;
import com.walker.aistock.backend.data.dto.req.StockNewsReq;
import com.walker.aistock.backend.data.dto.req.StockRecommendReq;
import com.walker.aistock.backend.data.dto.res.StockNewsRes;
import com.walker.aistock.backend.data.dto.res.StockRecommendRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FinnhubService {

    WebClientService webClientService;

    public List<StockNewsRes> stockNews(StockNewsReq stockNewsReq) {
        return webClientService.stockNews(stockNewsReq);
    }

    public StockRecommendRes stockRecommend(StockRecommendReq stockRecommendReq) {
        return webClientService.stockRecommendation(stockRecommendReq);
    }

}