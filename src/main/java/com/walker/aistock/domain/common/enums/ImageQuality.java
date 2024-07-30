package com.walker.aistock.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ImageQuality {

    STANDARD("standard"),
    HD("hd")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}