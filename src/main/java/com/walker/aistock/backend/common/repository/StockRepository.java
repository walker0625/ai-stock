package com.walker.aistock.backend.common.repository;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.front.dto.res.StockImageSpeechQueryDto;
import com.walker.aistock.front.dto.res.StockDetailsRes;
import com.walker.aistock.front.dto.res.StockImageSpeechRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, StockCustomRepository {

    Stock findByTicker(String ticker);

    List<Stock> findAllByOrderByIdDesc();

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
    List<StockImageSpeechRes> findStocksWithImagesAndSpeechesBetweenThreeDays(LocalDate twoDaysAgo, LocalDate today);

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
    StockDetailsRes findStockWithDetails(Long stockId, LocalDateTime startDate, LocalDateTime endDate);

}