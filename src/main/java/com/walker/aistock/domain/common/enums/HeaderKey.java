package com.walker.aistock.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Header {

    FINNHUB_TOKEN("X-Finnhub-Token"),
    AUTHORIZATION("Authorization")
    ;

    private String header;

}
