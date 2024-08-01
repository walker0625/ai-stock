package com.walker.aistock.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.walker.aistock.domain.common.entity.BaseTime;
import com.walker.aistock.domain.common.entity.Stock;
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
@Comment("주식 뉴스")
public class News extends BaseTime {

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("제목")
    String headline;

    @Comment("요약")
    String summary;

    @Comment("이미지 url")
    String image;

    @Comment("뉴스 url")
    String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

}