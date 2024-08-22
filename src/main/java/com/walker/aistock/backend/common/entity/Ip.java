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
@Comment("아이피")
public class Ip extends BaseTime {

    @Id
    @Column(name = "ip_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    Long id;

    @Comment("아이피")
    String ip;

    @Comment("접속 횟수")
    int count;

    @Builder
    public Ip(Long id, String ip, int count) {
        this.id = id;
        this.ip = ip;
        this.count = count;
    }

    public static Ip create(String ip) {
        return Ip.builder()
                    .ip(ip)
                    .count(1)
                .build();
    }

    public void increaseCount() {
        this.count++;
    }

}