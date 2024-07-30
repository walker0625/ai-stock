package com.walker.aistock.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    업데이트 되는 내용은 여기서 확인하여 적용 : https://platform.openai.com/docs/models
 */
@Getter
@AllArgsConstructor
public enum AskModel {

    GPT4_TURBO("gpt-4-turbo"),
    GPT4("gpt-4"),
    GPT3_TURBO("gpt-3.5-turbo")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}
