package com.walker.aistock.backend.data.entity;

import com.walker.aistock.backend.common.entity.BaseTime;
import com.walker.aistock.backend.data.dto.res.FearGreedRes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    String rating;

    @Comment("수치")
    int score;

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

    @Builder
    public FearGreed(Long id, String rating, int score, int previous, int previous1Week, int previous1Month, int previous1Year, LocalDateTime updateDate) {
        this.id = id;
        this.rating = rating;
        this.score = score;
        this.previous = previous;
        this.previous1Week = previous1Week;
        this.previous1Month = previous1Month;
        this.previous1Year = previous1Year;
        this.updateDate = updateDate;
    }

    public static FearGreed create(FearGreedRes fearGreedRes) {
        return FearGreed.builder()
                    .score(fearGreedRes.getScore())
                    .rating(fearGreedRes.getRating())
                    .previous(fearGreedRes.getPreviousClose())
                    .previous1Week(fearGreedRes.getPrevious1Week())
                    .previous1Month(fearGreedRes.getPrevious1Month())
                    .previous1Year(fearGreedRes.getPrevious1Year())
                    .updateDate(fearGreedRes.getTimestamp())
                .build();
    }

}
