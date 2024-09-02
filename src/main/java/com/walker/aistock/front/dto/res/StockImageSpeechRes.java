package com.walker.aistock.front.dto.res;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.enums.FileType;
import lombok.AccessLevel;
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
public class StockImageSpeechRes {

    Long stockId;
    String name;
    String ticker;
    String imagePath;
    String speechPath;
    LocalDate date;

    public StockImageSpeechRes(Long stockId, String name, String ticker, String imageFileKey, String speechFileKey, LocalDateTime date) {
        this.stockId = stockId;
        this.name = name;
        this.ticker = ticker;
        this.imagePath = IMAGE_SRC_PATH.getValue() + imageFileKey + JPG.getValue();
        this.speechPath = SPEECH_SRC_PATH.getValue() + speechFileKey + MP3.getValue();
        this.date = date.toLocalDate();
    }

}