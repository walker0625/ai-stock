package com.walker.aistock.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.walker.aistock.domain.common.enums.Tag.QUANTITATIVE_DATA;
import static com.walker.aistock.domain.common.enums.Tag.STOCK_NEWS;

@Getter
@AllArgsConstructor
public enum Prompt {

    // role
    ANALYST(" <role>You're a stock expert who's been an analyst since the start of the U.S. stock market. " +
                  " You also a capable investor with a 100 percent yield every year</role> "),
    TRANSLATOR(" <role>You are an expert who has translated English into Korea " +
                     " I have experience with writing a broadcast script, so I can translate it to make it easier to listen to when I voice it</role> "),
    ARTIST("<role>You are a witty and creative artist. You are professional investor who is well versed in American stocks.</role>"),

    // context
    TEXT_GUIDE(" <textGuide>Please take your time and respond slowly to future responses through sequential logical processes. " +
                     " If there's any inaccurate or unknown information, it's better to just say you don't know. " +
                     " Please explain it as long and detailed as possible. All responses should be in Korean</textGuide> "),

    // ask
    TICKER(" <tickerQuestion>What Are U.S. Stock Tickers For %s? Just answer the ticker</tickerQuestion> "),
    STOCK_QUESTION(" <stockQuestion>Please analyze " + QUANTITATIVE_DATA.getStart() + " I provided and compile an insightful report on the business " +
                         " by combining the industry situation/relationships with other companies/world situations. " +
                         " Additionally, please provide a short/medium/long term outlook for the stock and tell us your investment strategy accordingly</stockQuestion> "),
    NEWS_TRANSLATE(" <newsTranslate>Make a script and translate " + STOCK_NEWS.getStart() +
                         " into Korean as if the announcer were news</newsTranslate> "),
    IMAGE_DRAW("<imageDraw>Based on the contents of " + STOCK_NEWS.getStart() + ", please express the situation of the stock in a cute and witty character.</imageDraw>")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}