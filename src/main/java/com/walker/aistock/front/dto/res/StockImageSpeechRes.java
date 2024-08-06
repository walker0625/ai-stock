package com.walker.aistock.front.dto.res;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.enums.FileType;
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
public class StockImageSpeechRes {

    Long stockId;
    String name;
    String ticker;
    String imagePath;
    String speechPath;
    LocalDate date;

    public StockImageSpeechRes(Stock stock) {
        this.stockId = stock.getId();
        this.name = stock.getName();
        this.ticker = stock.getTicker();
        this.imagePath = String.format(IMAGE_SRC_PATH.getValue(), stock.getTodayImages().iterator().next().getImageFileKey(), FileType.JPG.getValue());
        this.speechPath = String.format(SPEECH_SRC_PATH.getValue(), stock.getSpeeches().iterator().next().getSpeechFileKey(), FileType.OPUS.getValue());
        this.date = stock.getTodayImages().iterator().next().getCreatedAt().toLocalDate();
    }

}
