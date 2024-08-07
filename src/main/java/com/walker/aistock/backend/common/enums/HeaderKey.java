package com.walker.aistock.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeaderKey {

    RAPID("x-rapidapi-key"),
    FINNHUB("X-Finnhub-Token"),
    AUTHORIZATION("Authorization")
    ;

    private final String value;

}
