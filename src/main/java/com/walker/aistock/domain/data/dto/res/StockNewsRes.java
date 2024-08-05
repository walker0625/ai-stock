package com.walker.aistock.domain.data.dto.res;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@JsonFilter("stockNewsFilter")
public class StockNewsRes {

    private String headline;
    private String summary;

    private String image;
    private String url;

}
