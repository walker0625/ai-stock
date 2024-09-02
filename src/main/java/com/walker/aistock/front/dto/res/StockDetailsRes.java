package com.walker.aistock.front.dto.res;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.enums.FileType;
import com.walker.aistock.backend.data.entity.Indicator;
import com.walker.aistock.backend.data.entity.Recommend;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.walker.aistock.backend.common.enums.FilePath.IMAGE_SRC_PATH;
import static com.walker.aistock.backend.common.enums.FilePath.SPEECH_SRC_PATH;
import static com.walker.aistock.backend.common.enums.FileType.*;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockDetailsRes {

    Long stockId;
    String name;
    String ticker;

    String imagePath;

    Double per;
    Double forwardPer;
    Double eps;
    Double forwardEps;
    Double peg;
    Double rsi;
    Double targetPrice;
    Double nowPrice;

    int strongBuy;
    int buy;
    int hold;
    int sell;
    int strongSell;

    String report;

    String speechPath;

    String script;

    LocalDate date;

    public StockDetailsRes(Long stockId, String name, String ticker,
                           Double per, Double forwardPer, Double eps, Double forwardEps, Double peg, Double rsi, Double targetPrice, Double nowPrice, LocalDateTime date,
                           int strongBuy, int buy, int hold, int sell, int strongSell,
                           String reportContent, String imageFileKey, String speechFileKey, String script) {

        this.stockId = stockId;
        this.name = name;
        this.ticker = ticker;

        this.per = per;
        this.forwardPer = forwardPer;
        this.eps = eps;
        this.forwardEps = forwardEps;
        this.peg = peg;
        this.rsi = rsi;
        this.targetPrice = targetPrice;
        this.nowPrice = nowPrice;
        this.date = date.toLocalDate();

        this.strongBuy = strongBuy;
        this.buy = buy;
        this.hold = hold;
        this.sell = sell;
        this.strongSell = strongSell;

        this.report = reportContent.replaceAll("[#*]", "");

        this.imagePath = IMAGE_SRC_PATH.getValue() + imageFileKey + JPG.getValue();

        this.speechPath = SPEECH_SRC_PATH.getValue() + speechFileKey + MP3.getValue();

        this.script = script;
    }

}