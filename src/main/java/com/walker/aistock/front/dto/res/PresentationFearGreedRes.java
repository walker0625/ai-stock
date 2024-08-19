package com.walker.aistock.front.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walker.aistock.backend.data.entity.FearGreed;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PresentationFearGreedRes {

    int score;
    String rating;
    LocalDateTime updateDateTime;

    int previousClose;
    int previous1Week;
    int previous1Month;
    int previous1Year;

    public PresentationFearGreedRes(FearGreed fearGreed) {
        this.score = fearGreed.getScore();
        this.rating = fearGreed.getRating();
        this.updateDateTime = fearGreed.getUpdateDate();
        this.previousClose = fearGreed.getPrevious();
        this.previous1Week = fearGreed.getPrevious1Week();
        this.previous1Month = fearGreed.getPrevious1Month();
        this.previous1Year = fearGreed.getPrevious1Year();
    }

}