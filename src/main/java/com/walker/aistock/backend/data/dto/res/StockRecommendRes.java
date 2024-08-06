package com.walker.aistock.backend.data.dto.res;

import lombok.Data;

@Data
public class StockRecommendRes {

    private String symbol;
    private String period;

    private int strongBuy;
    private int buy;
    private int hold;
    private int sell;
    private int strongSell;

}