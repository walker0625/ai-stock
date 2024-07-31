package com.walker.aistock.domain.data.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class StockNewsRes {

    private String headline;
    private String summary;

    @JsonIgnore
    private String image;

    @JsonIgnore
    private String url;

}
