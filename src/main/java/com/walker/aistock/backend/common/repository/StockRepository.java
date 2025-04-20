package com.walker.aistock.backend.common.repository;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.front.dto.res.StockDetailsRes;
import com.walker.aistock.front.dto.res.StockImageRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, StockCustomRepository {

    Stock findByTicker(String ticker);

    List<Stock> findAllByIsActiveTrueOrderByIdDesc();

    @Query(
        """
        SELECT new com.walker.aistock.front.dto.res.StockImageRes(
            st.id, st.name, st.ticker,
            ti.imageFileKey, ti.createdAt
        )
        FROM Stock st
        JOIN st.todayImages ti
        WHERE FUNCTION('DATE', ti.createdAt) BETWEEN :twoDaysAgo AND :today
        ORDER BY ti.createdAt DESC
        """
    )
    List<StockImageRes> findStocksWithImagesBetweenThreeDays(LocalDate twoDaysAgo, LocalDate today);

    @Query(
        """
        SELECT new com.walker.aistock.front.dto.res.StockDetailsRes(
            st.id, st.name, st.ticker,
            ii.per, ii.forwardPer, ii.eps, ii.forwardEps, ii.peg, ii.rsi, ii.targetPrice, ii.nowPrice, ii.createdAt,
            rc.strongBuy, rc.buy, rc.hold, rc.sell, rc.strongSell,
            re.content,
            ti.imageFileKey,
            nb.script
        )
        FROM Stock st
        JOIN st.indicators ii ON ii.createdAt BETWEEN :startDate AND :endDate
        JOIN st.todayImages ti ON ti.createdAt BETWEEN :startDate AND :endDate
        JOIN st.recommends rc ON rc.createdAt BETWEEN :startDate AND :endDate
        JOIN st.reports re ON re.createdAt BETWEEN :startDate AND :endDate
        JOIN st.newsBriefings nb ON nb.createdAt BETWEEN :startDate AND :endDate
        WHERE st.id = :stockId
        """
    )
    StockDetailsRes findStockWithDetails(Long stockId, LocalDateTime startDate, LocalDateTime endDate);

}