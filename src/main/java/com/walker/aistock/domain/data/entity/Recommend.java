package com.walker.aistock.domain.data.entity;

import com.walker.aistock.domain.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Comment("주식 추천 아이디")
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

    @Comment("주식 추천 일자")
    String recommendDate;

}
