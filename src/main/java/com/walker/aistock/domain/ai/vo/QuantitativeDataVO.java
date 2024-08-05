package com.walker.aistock.domain.ai.vo;

import com.walker.aistock.domain.data.dto.res.FearGreedRes;
import com.walker.aistock.domain.data.dto.res.FinvizDetailRes;
import com.walker.aistock.domain.data.dto.res.StockRecommendRes;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuantitativeDataVO {

    private String ticker;

    private int fearAndGreedIndex;
    private String fearAndGreedStatus;
    private LocalDateTime fearAndGreedUpdateDate;

    private int previousFearAndGreedIndex;
    private int previous1WeekFearAndGreedIndex;
    private int previous1MonthFearAndGreedIndex;
    private int previous1YearFearAndGreedIndex;

    private Double per;
    private Double forwardPer;
    private Double eps;
    private Double forwardEps;
    private Double peg;
    private Double rsi;
    private Double targetPrice;
    private Double nowPrice;

    //private String symbol;
    private String recommendDate;

    private int strongBuy;
    private int buy;
    private int hold;
    private int sell;
    private int strongSell;

    public QuantitativeDataVO(String ticker, FinvizDetailRes finvizDetailRes, StockRecommendRes stockRecommendRes) {
        this.ticker = ticker;

        this.per = finvizDetailRes.getPer();
        this.forwardPer = finvizDetailRes.getFper();
        this.eps = finvizDetailRes.getEps();
        this.forwardEps = finvizDetailRes.getFeps();
        this.peg = finvizDetailRes.getPeg();
        this.rsi = finvizDetailRes.getRsi();
        this.targetPrice = finvizDetailRes.getTargetPrice();
        this.nowPrice = finvizDetailRes.getNowPrice();

        this.recommendDate = stockRecommendRes.getPeriod();
        this.strongBuy = stockRecommendRes.getStrongBuy();
        this.buy = stockRecommendRes.getBuy();
        this.hold = stockRecommendRes.getHold();
        this.sell = stockRecommendRes.getSell();
        this.strongSell = stockRecommendRes.getStrongSell();
    }

}