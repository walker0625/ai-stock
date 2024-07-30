package com.walker.aistock.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpeechModel {

    TTS1("tts-1"),
    TTS1_HD("tts-1-hd")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}