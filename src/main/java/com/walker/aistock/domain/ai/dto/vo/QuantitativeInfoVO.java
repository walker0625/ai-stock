package com.walker.aistock.domain.ai.dto.vo;

import com.walker.aistock.domain.data.dto.res.FearGreedRes;
import com.walker.aistock.domain.data.dto.res.FinvizDetailRes;
import com.walker.aistock.domain.data.dto.res.StockRecommendRes;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuantitativeInfoVO {

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

    public QuantitativeInfoVO(String ticker, FearGreedRes fearGreedRes, FinvizDetailRes finvizDetailRes,
                              List<StockRecommendRes> stockRecommendRes) {
        this.ticker = ticker;

        this.fearAndGreedIndex = fearGreedRes.getScore();
        this.fearAndGreedStatus = fearGreedRes.getRating();
        this.previousFearAndGreedIndex = fearGreedRes.getPreviousClose();
        this.previous1WeekFearAndGreedIndex = fearGreedRes.getPrevious1Week();
        this.previous1MonthFearAndGreedIndex = fearGreedRes.getPrevious1Month();
        this.previous1YearFearAndGreedIndex = fearGreedRes.getPrevious1Year();
        this.fearAndGreedUpdateDate = fearGreedRes.getTimestamp();

        this.per = finvizDetailRes.getPer();
        this.forwardPer = finvizDetailRes.getFper();
        this.eps = finvizDetailRes.getEps();
        this.forwardEps = finvizDetailRes.getFeps();
        this.peg = finvizDetailRes.getPeg();
        this.rsi = finvizDetailRes.getRsi();
        this.targetPrice = finvizDetailRes.getTargetPrice();
        this.nowPrice = finvizDetailRes.getNowPrice();

        StockRecommendRes latestStockRecommend = stockRecommendRes.getFirst();
        this.recommendDate = latestStockRecommend.getPeriod();
        this.strongBuy = latestStockRecommend.getStrongBuy();
        this.buy = latestStockRecommend.getBuy();
        this.hold = latestStockRecommend.getHold();
        this.sell = latestStockRecommend.getSell();
        this.strongSell = latestStockRecommend.getStrongSell();
    }

}
