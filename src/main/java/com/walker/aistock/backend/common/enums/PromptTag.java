package com.walker.aistock.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromptTag {

    QUANTITATIVE_DATA("<quantitativeData>", "</quantitativeData>"),
    STOCK_NEWS("<stockNews>", "</stockNews>")
    ;

    private final String start;
    private final String end;

}
