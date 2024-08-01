package com.walker.aistock.domain.data.entity;

import com.walker.aistock.domain.common.entity.BaseTime;
import com.walker.aistock.domain.common.entity.Stock;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

}