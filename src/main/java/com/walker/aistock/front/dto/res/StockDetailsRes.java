package com.walker.aistock.front.dto.res;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.enums.FileType;
import com.walker.aistock.backend.data.entity.Indicator;
import com.walker.aistock.backend.data.entity.Recommend;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static com.walker.aistock.backend.common.enums.FilePath.IMAGE_SRC_PATH;
import static com.walker.aistock.backend.common.enums.FilePath.SPEECH_SRC_PATH;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockDetailsRes {

    Long stockId;
    String name;
    String ticker;

    String imagePath;

    private Double per;
    private Double forwardPer;
    private Double eps;
    private Double forwardEps;
    private Double peg;
    private Double rsi;
    private Double targetPrice;
    private Double nowPrice;

    int strongBuy;
    int buy;
    int hold;
    int sell;
    int strongSell;

    String report;

    String speechPath;

    String script;

    LocalDate date;

    public StockDetailsRes(Stock stock) {
        this.stockId = stock.getId();
        this.name = stock.getName();
        this.ticker = stock.getTicker();

        this.imagePath = String.format(IMAGE_SRC_PATH.getValue(), stock.getTodayImages().iterator().next().getImageFileKey(), FileType.JPG.getValue());

        Indicator indicator = stock.getIndicators().iterator().next();
        this.per = indicator.getPer();
        this.forwardPer = indicator.getForwardPer();
        this.eps = indicator.getEps();
        this.forwardEps = indicator.getForwardEps();
        this.peg = indicator.getPeg();
        this.rsi = indicator.getRsi();
        this.targetPrice = indicator.getTargetPrice();
        this.nowPrice = indicator.getNowPrice();
        this.date = indicator.getCreatedAt().toLocalDate();

        Recommend recommend = stock.getRecommends().iterator().next();
        this.strongBuy = recommend.getStrongBuy();
        this.buy = recommend.getBuy();
        this.hold = recommend.getHold();
        this.sell = recommend.getSell();
        this.strongSell = recommend.getStrongSell();

        this.report = stock.getReports().iterator().next().getContent().replaceAll("[#*]", "");

        this.speechPath = String.format(SPEECH_SRC_PATH.getValue(), stock.getSpeeches().iterator().next().getSpeechFileKey(), FileType.OPUS.getValue());

        this.script = stock.getNewsBriefings().iterator().next().getScript();
    }

}
