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

    private int score;
    private String rating;
    private LocalDateTime updateDateTime;

    private int previousClose;
    private int previous1Week;
    private int previous1Month;
    private int previous1Year;

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