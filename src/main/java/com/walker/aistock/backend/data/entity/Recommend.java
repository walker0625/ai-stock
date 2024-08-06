package com.walker.aistock.backend.data.entity;

import com.walker.aistock.backend.common.entity.BaseTime;
import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.data.dto.res.StockRecommendRes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ToString(exclude = "")
@Comment("주식 추천")
public class Recommend extends BaseTime {

    @Id
    @Column(name = "recommend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("적극 매수")
    int strongBuy;

    @Comment("매수")
    int buy;

    @Comment("보유")
    int hold;

    @Comment("매도")
    int sell;

    @Comment("적극 매도")
    int strongSell;

    @Comment("추천 일자")
    String recommendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

    @Builder
    public Recommend(Long id, int strongBuy, int buy, int hold, int sell, int strongSell, String recommendDate, Stock stock) {
        this.id = id;
        this.strongBuy = strongBuy;
        this.buy = buy;
        this.hold = hold;
        this.sell = sell;
        this.strongSell = strongSell;
        this.recommendDate = recommendDate;
        this.stock = stock;
    }

    public static Recommend create(StockRecommendRes stockRecommendRes, Stock stock) {
        return Recommend.builder()
                    .strongBuy(stockRecommendRes.getStrongBuy())
                    .buy(stockRecommendRes.getBuy())
                    .hold(stockRecommendRes.getHold())
                    .sell(stockRecommendRes.getSell())
                    .strongSell(stockRecommendRes.getStrongSell())
                    .recommendDate(stockRecommendRes.getPeriod())
                    .stock(stock)
                .build();
    }

    public void modify(StockRecommendRes stockRecommendRes) {
        this.strongBuy = stockRecommendRes.getStrongBuy();
        this.buy = stockRecommendRes.getBuy();
        this.hold = stockRecommendRes.getHold();
        this.sell = stockRecommendRes.getSell();
        this.strongSell = stockRecommendRes.getStrongSell();
    }

}