package com.walker.aistock.backend.common.repository;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.front.dto.res.StockDetailsRes;
import com.walker.aistock.front.dto.res.StockImageSpeechRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// TODO 빌드 환경이나 JPA 버전에 따라 파라미터 추론이 불가한 경우가 있어 @Param 추가하는 것 권장
@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, StockCustomRepository {

    Stock findByTicker(String ticker);

    List<Stock> findAllByIsActiveTrueOrderByIdDesc();

    @Query(
        """
        SELECT new com.walker.aistock.front.dto.res.StockImageSpeechRes(
            st.id, st.name, st.ticker,
            ti.imageFileKey,
            sp.speechFileKey, sp.createdAt
        )
        FROM Stock st
        JOIN st.todayImages ti
        JOIN st.speeches sp
        WHERE FUNCTION('DATE', ti.createdAt) = FUNCTION('DATE', sp.createdAt)
        AND FUNCTION('DATE', ti.createdAt) BETWEEN :twoDaysAgo AND :today
        AND FUNCTION('DATE', sp.createdAt) BETWEEN :twoDaysAgo AND :today
        ORDER BY sp.createdAt DESC
        """
    )
    List<StockImageSpeechRes> findStocksWithImagesAndSpeechesBetweenThreeDays(@Param("twoDaysAgo") LocalDate twoDaysAgo, @Param("today") LocalDate today);

    @Query(
        """
        SELECT new com.walker.aistock.front.dto.res.StockDetailsRes(
            st.id, st.name, st.ticker,
            ii.per, ii.forwardPer, ii.eps, ii.forwardEps, ii.peg, ii.rsi, ii.targetPrice, ii.nowPrice, ii.createdAt,
            rc.strongBuy, rc.buy, rc.hold, rc.sell, rc.strongSell,
            re.content,
            ti.imageFileKey,
            sp.speechFileKey,
            nb.script
        )
        FROM Stock st
        JOIN st.indicators ii ON ii.createdAt BETWEEN :startDate AND :endDate
        JOIN st.todayImages ti ON ti.createdAt BETWEEN :startDate AND :endDate
        JOIN st.recommends rc ON rc.createdAt BETWEEN :startDate AND :endDate
        JOIN st.reports re ON re.createdAt BETWEEN :startDate AND :endDate
        JOIN st.speeches sp ON sp.createdAt BETWEEN :startDate AND :endDate
        JOIN st.newsBriefings nb ON nb.createdAt BETWEEN :startDate AND :endDate
        WHERE st.id = :stockId
        """
    )
    StockDetailsRes findStockWithDetails(@Param("stockId") Long stockId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}