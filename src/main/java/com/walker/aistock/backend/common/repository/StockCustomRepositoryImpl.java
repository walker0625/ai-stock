package com.walker.aistock.backend.common.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.walker.aistock.backend.ai.entity.QSpeech.*;
import static com.walker.aistock.backend.ai.entity.QTodayImage.todayImage;
import static com.walker.aistock.backend.common.entity.QStock.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StockCustomRepositoryImpl implements StockCustomRepository {

    JPAQueryFactory queryFactory;

}
