package com.walker.aistock.domain.data.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.walker.aistock.domain.common.enums.Url.FINNHUB_RECOMMEND;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRecommendReq {

    private String symbol;

    public String getStockRecommendUrlWithParam() {
        return String.format(
                FINNHUB_RECOMMEND.getValue(),
                this.symbol
        );
    }

}
