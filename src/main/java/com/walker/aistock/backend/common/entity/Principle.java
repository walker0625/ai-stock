package com.walker.aistock.backend.common.entity;

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
@Comment("투자 원칙")
public class Principle extends BaseTime {

    @Id
    @Column(name = "principle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Lob
    @Comment("내용")
    @Column(columnDefinition = "TEXT")
    String content;

    @Builder
    public Principle(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static Principle create(String script) {
        return Principle.builder()
                    .content(script)
                .build();
    }

}