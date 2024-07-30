package com.walker.aistock.domain.ai.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockPromptDataVO {

    private QuantitativeInfoVO quantitativeInfo;
    private QualitativeInfoVO qualitativeInfo;

    public String makePromptString() {
        return quantitativeInfo.toString() + qualitativeInfo.toString();
    }

}
