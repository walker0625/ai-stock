package com.walker.aistock.domain.ai.entity;

import com.walker.aistock.domain.common.entity.BaseTime;
import com.walker.aistock.domain.common.entity.Stock;
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
    String fileKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

    @Builder
    public Speech(Long id, String fileKey, Stock stock) {
        this.id = id;
        this.fileKey = fileKey;
        this.stock = stock;
    }

    public static Speech create(String speechKey, Stock stock) {
        return Speech
                .builder()
                    .fileKey(speechKey)
                    .stock(stock)
                .build();
    }

}