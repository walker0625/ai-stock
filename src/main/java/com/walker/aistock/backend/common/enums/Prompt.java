package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.walker.aistock.backend.common.enums.PromptTag.QUANTITATIVE_DATA;
import static com.walker.aistock.backend.common.enums.PromptTag.STOCK_NEWS;

@Getter
@AllArgsConstructor
public enum Prompt {

    // role
    ANALYST(" <role>You're a stock expert who's been an analyst since the start of the U.S. stock market. " +
                  " You also a capable investor with a 100 percent yield every year</role> "),
    TRANSLATOR(" <role>You are an expert who has translated English into Korea " +
                     " I have experience with writing a broadcast script, so I can translate it to make it easier to listen to when I voice it</role> "),
    ARTIST(" <role>You are a witty and creative artist. You are professional investor who is well versed in American stocks.</role> "),

    // context
    TEXT_GUIDE(" <textGuide>Please take your time and respond slowly to future responses through sequential logical processes. " +
                     " If there's any inaccurate or unknown information, it's better to just say you don't know. " +
                     " Please explain it as long and detailed as possible. All responses should be in Korean</textGuide> "),

    // ask
    TICKER(" <tickerQuestion>Please let me know the name of the U.S. stock that this %s means and ticker. " +
                 " Please answer with a string of name:ticker</tickerQuestion> "),
    STOCK_QUESTION(" <stockQuestion>Please analyze " + QUANTITATIVE_DATA.getStart() + " I provided and compile an insightful report on the business " +
                         " by combining the industry situation/relationships with other companies/world situations. " +
                         " Additionally, please provide a short/medium/long term outlook for the stock and tell us your investment strategy accordingly</stockQuestion> "),
    NEWS_TRANSLATE(" <newsTranslate>Make a script and translate " + STOCK_NEWS.getStart() +
                         " into Korean as if the announcer were news</newsTranslate> "),
    IMAGE_DRAW(" <imageDraw>Based on the contents of " + STOCK_NEWS.getStart() + ", " +
                     " please express the situation of the stock in a cute and witty character.</imageDraw> "),

    // etc
    NEWS_EXCEPT(" <newsExcept>Please remove and provide information similar to the following in the resulting sentence. " +
                      " 1. All sentences on Zacks.com (introduction, access recommendation, promotion, etc.) " +
                      " 2. I'm [name], the news presenter.</newsExcept> "),
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}