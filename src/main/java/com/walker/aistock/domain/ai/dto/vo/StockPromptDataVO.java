package com.walker.aistock.domain.ai.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockPromptData {

    private QuantitativeInfo quantitativeInfo;
    private QualitativeInfo qualitativeInfo;

    public String makePromptString() {
        return quantitativeInfo.toString() + qualitativeInfo.toString();
    }

}
