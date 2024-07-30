package com.walker.aistock.domain.ai.dto.vo;

import com.walker.aistock.domain.data.dto.res.StockNewsRes;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QualitativeInfo {

    private List<String> headline = new ArrayList<>();
    private List<String> summary = new ArrayList<>();

    // TODO 하나의 쌍으로 묶어서 배열화하는 것으로 변경
    public QualitativeInfo(List<StockNewsRes> stockNewsRes) {
        for (StockNewsRes news : stockNewsRes) {
            headline.add(news.getHeadline());
            summary.add(news.getSummary());
        }
    }
}
