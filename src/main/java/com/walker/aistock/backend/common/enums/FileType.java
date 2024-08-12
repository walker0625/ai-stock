package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileType {

    // IMAGE
    JPG(".jpg"),
    PNG(".png"),

    // AUDIO
    OPUS(".opus"),
    MP3(".mp3")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}