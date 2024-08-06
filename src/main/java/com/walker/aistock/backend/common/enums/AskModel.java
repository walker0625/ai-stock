package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    업데이트 되는 내용은 여기서 확인하여 적용 : https://platform.openai.com/docs/models
 */
@Getter
@AllArgsConstructor
public enum AskModel {

    GPT4_O("gpt-4o"),
    GPT4_MINI("gpt-4o-mini")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
