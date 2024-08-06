package com.walker.aistock.backend.data.entity;

import com.walker.aistock.backend.common.entity.BaseTime;
import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.data.dto.res.StockNewsRes;
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
@Comment("주식 뉴스")
public class News extends BaseTime {

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("제목")
    String headline;

    @Lob
    @Comment("요약")
    @Column(columnDefinition = "TEXT")
    String summary;

    @Comment("이미지 url")
    String image;

    @Comment("뉴스 url")
    String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

    @Builder
    public News(Long id, String headline, String summary, String image, String url, Stock stock) {
        this.id = id;
        this.headline = headline;
        this.summary = summary;
        this.image = image;
        this.url = url;
        this.stock = stock;
    }

    public static News create(StockNewsRes stockNewsRes, Stock stock) {
        return News.builder()
                    .headline(stockNewsRes.getHeadline())
                    .summary(stockNewsRes.getSummary().isEmpty() ? null : stockNewsRes.getSummary())
                    .image(stockNewsRes.getImage().isEmpty() ? null : stockNewsRes.getImage())
                    .url(stockNewsRes.getUrl().isEmpty() ? null : stockNewsRes.getUrl())
                    .stock(stock)
                .build();
    }

}