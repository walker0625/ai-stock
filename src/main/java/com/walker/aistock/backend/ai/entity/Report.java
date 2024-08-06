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
@Comment("주식 보고서")
public class Report extends BaseTime {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Lob
    @Comment("내용")
    @Column(columnDefinition = "TEXT")
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

    @Builder
    public Report(Long id, String content, Stock stock) {
        this.id = id;
        this.content = content;
        this.stock = stock;
    }

    public static Report create(String content, Stock stock) {
        return Report
                .builder()
                    .content(content)
                    .stock(stock)
                .build();
    }

}
