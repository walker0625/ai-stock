package com.walker.aistock.domain.data.dto.res;

import lombok.Data;

@Data
public class FinvizDetailRes {

    private Double per;
    private Double fper;
    private Double eps;
    private Double feps;
    private Double peg;
    private Double rsi;
    private Double targetPrice;
    private Double nowPrice;

}
