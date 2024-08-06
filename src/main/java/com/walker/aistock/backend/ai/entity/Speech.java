package com.walker.aistock.backend.ai.entity;

import com.walker.aistock.backend.common.entity.BaseTime;
import com.walker.aistock.backend.common.entity.Stock;
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
@Comment("주식 분석 음성")
public class Speech extends BaseTime {

    @Id
    @Column(name = "speech_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("음성 파일키 - uuid")
    String speechFileKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

    @Builder
    public Speech(Long id, String speechFileKey, Stock stock) {
        this.id = id;
        this.speechFileKey = speechFileKey;
        this.stock = stock;
    }

    public static Speech create(String speechFileKey, Stock stock) {
        return Speech
                .builder()
                    .speechFileKey(speechFileKey)
                    .stock(stock)
                .build();
    }

}