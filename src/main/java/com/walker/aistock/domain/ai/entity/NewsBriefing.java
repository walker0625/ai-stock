package com.walker.aistock.domain.ai.entity;

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
@Comment("주식 뉴스 브리핑")
public class NewsBriefing extends BaseTime {

    @Id
    @Column(name = "news_briefing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("원고")
    String script;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

}