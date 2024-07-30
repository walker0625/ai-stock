package com.walker.aistock.domain.data.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static java.time.format.DateTimeFormatter.*;

@Data
public class FearGreedRes {

    private int score;
    private String rating;
    private LocalDateTime timestamp;

    @JsonProperty("previous_close")
    private int previousClose;
    @JsonProperty("previous_1_week")
    private int previous1Week;
    @JsonProperty("previous_1_month")
    private int previous1Month;
    @JsonProperty("previous_1_year")
    private int previous1Year;

    // TODO "2022-06-02T18:05:08.608000+00:00" 이 형태로 넘어온 value를 LocalDateTime에 mapping함(좀 더 간단하고 직관적인 방식으로 변경 요망)
    public void setTimestamp(String timestamp) {
        this.timestamp = OffsetDateTime.parse(timestamp, ISO_OFFSET_DATE_TIME).toLocalDateTime();
    }

}
