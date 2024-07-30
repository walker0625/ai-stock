package com.walker.aistock.domain.ai.dto.vo;

import com.walker.aistock.domain.data.dto.res.StockNewsRes;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QualitativeInfoVO {

    private List<NewsVO> newses = new ArrayList<>();

    public QualitativeInfoVO(List<StockNewsRes> stockNewsRes) {
        for (StockNewsRes news : stockNewsRes) {
            newses.add(new NewsVO(news.getHeadline(), news.getSummary()));
        }
    }

}
