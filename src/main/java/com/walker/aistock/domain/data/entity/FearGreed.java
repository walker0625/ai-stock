package com.walker.aistock.domain.data.entity;

import com.walker.aistock.domain.common.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
//@ToString(exclude = "")
@Comment("공탐 지수")
public class FearGreed extends BaseTime {

    @Id
    @Column(name = "feargreed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("상태값")
    String status;

    @Comment("수치")
    int rate;

    @Comment("이전 수치")
    int previous;

    @Comment("1주전 수치")
    int previous1Week;

    @Comment("1달전 수치")
    int previous1Month;

    @Comment("1년전 수치")
    int previous1Year;

    @Comment("갱신 일시")
    LocalDateTime updateDate;

}
