package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageResponseFormat {

    URL("url"),
    BASE64("b64_json")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}