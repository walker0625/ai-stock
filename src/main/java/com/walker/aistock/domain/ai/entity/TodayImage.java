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
@Comment("주식 오늘의 이미지")
public class TodayImage extends BaseTime {

    @Id
    @Column(name = "today_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("이미지 파일키 - uuid")
    String fileKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @Comment("주식 아이디")
    Stock stock;

}
