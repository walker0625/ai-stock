package com.walker.aistock.domain.data.entity;

import com.walker.aistock.domain.common.entity.BaseTime;
import com.walker.aistock.domain.common.entity.Stock;
import com.walker.aistock.domain.data.dto.res.FinvizDetailRes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ToString(exclude = "")
@Comment("주식 지표")
public class Indicator extends BaseTime {

    @Id
    @Column(name = "indicator_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("주가 수익 비율")
    private Double per;

    @Comment("미래 주가 수익 비율")
    private Double forwardPer;

    @Comment("주당 순이익")
    private Double eps;

    @Comment("미래 주당 순이익")
    private Double forwardEps;

    @Comment("성장 반영 주가 수익 비율")
    private Double peg;

    @Comment("주가 강도 지수")
    private Double rsi;

    @Comment("목표 주가")
    private Double targetPrice;

    @Comment("현재 주가")
    private Double nowPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

    @Builder
    public Indicator(Long id, Double per, Double forwardPer, Double eps, Double forwardEps, Double peg, Double rsi, Double targetPrice, Double nowPrice, Stock stock) {
        this.id = id;
        this.per = per;
        this.forwardPer = forwardPer;
        this.eps = eps;
        this.forwardEps = forwardEps;
        this.peg = peg;
        this.rsi = rsi;
        this.targetPrice = targetPrice;
        this.nowPrice = nowPrice;
        this.stock = stock;
    }

    public static Indicator create(FinvizDetailRes finvizDetailRes, Stock stock) {
        return Indicator.builder()
                    .per(finvizDetailRes.getPer())
                    .forwardPer(finvizDetailRes.getFper())
                    .eps(finvizDetailRes.getEps())
                    .forwardEps(finvizDetailRes.getFeps())
                    .peg(finvizDetailRes.getPeg())
                    .rsi(finvizDetailRes.getRsi())
                    .targetPrice(finvizDetailRes.getTargetPrice())
                    .nowPrice(finvizDetailRes.getNowPrice())
                    .stock(stock)
            .build();
    }

}