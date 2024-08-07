package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpeechVoice {

    ALLOY("alloy"),
    ECHO("echo"), // 남 추천
    FABLE("fable"),
    ONYX("onyx"),
    NOVA("nova"), // 여 추천
    SHIMMER("shimmer")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
