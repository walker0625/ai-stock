package com.walker.aistock.domain.ai.entity;

import com.walker.aistock.domain.common.entity.BaseTime;
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
    @Comment("주식 오늘의 이미지 아이디")
    Long id;

}
