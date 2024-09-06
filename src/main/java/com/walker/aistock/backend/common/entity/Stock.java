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

import java.util.*;

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

    @Comment("활성화 여부")
    boolean isActive;

    @OneToMany(mappedBy = "stock")
    List<Indicator> indicators = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<Recommend> recommends = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<News> newses = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<NewsBriefing> newsBriefings = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<Speech> speeches = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<TodayImage> todayImages = new ArrayList<>();

    @Builder
    public Stock(Long id, String name, String ticker, boolean isActive,
                 List<Indicator> indicators, List<Recommend> recommends,
                 List<News> newses, List<Report> reports, List<NewsBriefing> newsBriefings,
                 List<Speech> speeches, List<TodayImage> todayImages) {
        this.id = id;
        this.name = name;
        this.ticker = ticker;
        this.isActive = isActive;
        this.indicators = indicators;
        this.recommends = recommends;
        this.newses = newses;
        this.reports = reports;
        this.newsBriefings = newsBriefings;
        this.speeches = speeches;
        this.todayImages = todayImages;
    }

    public Stock(String name, String ticker, boolean isActive) {
        this.name = name;
        this.ticker = ticker;
        this.isActive = isActive;
    }

}