package com.walker.aistock.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Url {

    FINVIZ_DETAIL("https://finviz.com/quote.ashx?p=d&t=%s"),

    RAPID_FEARGREED("https://fear-and-greed-index2.p.rapidapi.com/index"),

    FINNHUB_NEWS("https://finnhub.io/api/v1/company-news?symbol=%s&from=%s&to=%s"),
    FINNHUB_RECOMMEND("https://finnhub.io/api/v1/stock/recommendation?symbol=%s"),

    CHATGPT_ASK("https://api.openai.com/v1/chat/completions"),
    CHATGPT_IMAGE("https://api.openai.com/v1/images/generations"),
    CHATGPT_SPEECH("https://api.openai.com/v1/audio/speech");

    private final String value;

}
