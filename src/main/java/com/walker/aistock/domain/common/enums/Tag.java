package com.walker.aistock.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {

    QUANTITATIVE_DATA("<quantitativeData>", "</quantitativeData>"),
    STOCK_NEWS("<stockNews>", "</stockNews>")
    ;

    private final String start;
    private final String end;

}
