package com.walker.aistock.domain.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Url {

    FINVIZ_STOCK_DETAIL("https://finviz.com/quote.ashx?p=d&t="),
    FINNHUB_STOCK_NEWS("https://finnhub.io/api/v1/company-news?symbol=%s&from=%s&to=%s"),
    FINNHUB_STOCK_RECOMMEND("https://finnhub.io/api/v1/stock/recommendation?symbol=%s"),

    CHATGPT_ASK("https://api.openai.com/v1/chat/completions"),
    CHATGPT_IMAGE("https://api.openai.com/v1/images/generations"),
    CHATGPT_SPEECH("https://api.openai.com/v1/audio/speech");

    private String url;

}
