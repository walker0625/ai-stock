package com.walker.aistock.backend.data.dto.res;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

@Data
@JsonFilter("stockNewsFilter")
public class StockNewsRes {

    private String headline;
    private String summary;

    private String image;
    private String url;

}
