package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ImageSize {

    DALLE2_SMALL("256x256"),
    DALLE2_MEDIUM("512x512"),
    DALLE2_LARGE("1024x1024"),
    DALLE3_MEDIUM("1024x1024"),
    DALLE3_LARGE_WIDTH("1792x1024"),
    DALLE3_LARGE_HEIGHT("1024x1792")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
