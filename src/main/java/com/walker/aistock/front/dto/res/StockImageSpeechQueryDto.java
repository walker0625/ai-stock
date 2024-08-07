package com.walker.aistock.front.dto.res;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockImageSpeechQueryDto {

    Long stockId;
    String name;
    String ticker;
    String imageFileKey;
    String speechFileKey;
    LocalDateTime speechCreatedAt;

}