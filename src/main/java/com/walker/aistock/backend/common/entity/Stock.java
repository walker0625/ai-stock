package com.walker.aistock.backend.common.entity;

import com.walker.aistock.backend.ai.entity.NewsBriefing;
import com.walker.aistock.backend.ai.entity.Report;
import com.walker.aistock.backend.ai.entity.Speech;
import com.walker.aistock.backend.ai.entity.TodayImage;
import com.walker.aistock.backend.data.entity.Indicator;
import com.walker.aistock.backend.data.entity.News;
import com.walker.aistock.backend.data.entity.Recommend;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Comment("주식")
public class Stock extends BaseTime {

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("이름")
    String name;

    @Comment("기호")
    String ticker;

    // MultipleBagFetchException 문제로 인하여 List > Set 으로 변경
    @OneToMany(mappedBy = "stock")
    Set<Indicator> indicators = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock")
    Set<Recommend> recommends = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock")
    Set<News> newses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock")
    Set<Report> reports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock")
    Set<NewsBriefing> newsBriefings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock")
    Set<Speech> speeches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "stock")
    Set<TodayImage> todayImages = new LinkedHashSet<>();

    @Builder
    public Stock(Long id, String name, String ticker,
                 Set<Indicator> indicators, Set<Recommend> recommends,
                 Set<News> newses, Set<Report> reports, Set<NewsBriefing> newsBriefings,
                 Set<Speech> speeches, Set<TodayImage> todayImages) {
        this.id = id;
        this.name = name;
        this.ticker = ticker;
        this.indicators = indicators;
        this.recommends = recommends;
        this.newses = newses;
        this.reports = reports;
        this.newsBriefings = newsBriefings;
        this.speeches = speeches;
        this.todayImages = todayImages;
    }

    public Stock(String name, String ticker) {
        this.name = name;
        this.ticker = ticker;
    }

}
