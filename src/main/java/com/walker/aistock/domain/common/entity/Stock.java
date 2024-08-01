package com.walker.aistock.domain.common.entity;

import com.walker.aistock.domain.ai.entity.NewsBriefing;
import com.walker.aistock.domain.ai.entity.Report;
import com.walker.aistock.domain.ai.entity.Speech;
import com.walker.aistock.domain.ai.entity.TodayImage;
import com.walker.aistock.domain.data.entity.Indicator;
import com.walker.aistock.domain.data.entity.News;
import com.walker.aistock.domain.data.entity.Recommend;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ToString(exclude = "")
@Comment("주식")
public class Stock extends BaseTime {

    @Id
    @Column(name = "stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("기호")
    String ticker;

    @OneToMany(mappedBy = "stock")
    List<Indicator> indicatorList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<Recommend> recommendList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<News> newsList= new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<Report> reportList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<NewsBriefing> newsBriefingList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<Speech> speechList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    List<TodayImage> todayImageList = new ArrayList<>();

    @Builder
    public Stock(Long id, String ticker, List<Indicator> indicatorList, List<Recommend> recommendList,
                 List<News> newsList, List<Report> reportList, List<NewsBriefing> newsBriefingList,
                 List<Speech> speechList, List<TodayImage> todayImageList) {
        this.id = id;
        this.ticker = ticker;
        this.indicatorList = indicatorList;
        this.recommendList = recommendList;
        this.newsList = newsList;
        this.reportList = reportList;
        this.newsBriefingList = newsBriefingList;
        this.speechList = speechList;
        this.todayImageList = todayImageList;
    }

    public Stock(String ticker) {
        this.ticker = ticker;
    }

}