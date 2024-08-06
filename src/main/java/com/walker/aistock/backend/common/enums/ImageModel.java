package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageModel {

    DALLE2("dall-e-2"),
    DALLE3("dall-e-3")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
