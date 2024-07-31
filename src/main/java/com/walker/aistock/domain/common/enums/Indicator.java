package com.walker.aistock.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Indicator {

    PER("Price-to-Earnings (ttm)"),
    FORWARD_PER("Forward Price-to-Earnings (next fiscal year)"),
    EPS("Diluted EPS (ttm)"),
    FORWARD_EPS("EPS estimate for next year"),
    PEG("Price-to-Earnings-to-Growth"),
    RSI("Relative Strength Index"),
    TARGET_PRICE("Analysts' mean target price"),
    NOW_PRICE("Current stock price"),
    ;

    private final String attr;

}
