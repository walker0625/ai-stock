package com.walker.aistock.backend.common.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walker.aistock.backend.ai.entity.QSpeech;
import com.walker.aistock.backend.ai.entity.QTodayImage;
import com.walker.aistock.backend.common.entity.QStock;
import com.walker.aistock.front.dto.res.QStockImageSpeechRes;
import com.walker.aistock.front.dto.res.StockImageSpeechRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import static com.walker.aistock.backend.ai.entity.QSpeech.*;
import static com.walker.aistock.backend.ai.entity.QTodayImage.todayImage;
import static com.walker.aistock.backend.common.entity.QStock.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StockCustomRepositoryImpl implements StockCustomRepository {

    JPAQueryFactory queryFactory;

}
