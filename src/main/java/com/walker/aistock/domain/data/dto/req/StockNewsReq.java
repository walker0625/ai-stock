package com.walker.aistock.domain.data.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.walker.aistock.domain.common.enums.Url.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockNewsReq {

    private String symbol;
    private String from;
    private String to;

    public String getStockNewsUrlWithParam() {
        return String.format(
                FINNHUB_NEWS.getValue(),
                this.symbol,
                this.from,
                this.to
        );
    }

}
