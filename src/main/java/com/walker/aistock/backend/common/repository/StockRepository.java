package com.walker.aistock.backend.common.repository;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.front.dto.res.StockImageSpeechQueryDto;
import com.walker.aistock.front.dto.res.StockDetailsRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// TODO 전체적으로 좀 더 가독성있고 효율적으로 리팩토링 요망
@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, StockCustomRepository {

    Stock findByTicker(String ticker);

    @Query(
        """
        SELECT new com.walker.aistock.front.dto.res.StockImageSpeechQueryDto(st.id, st.name, st.ticker, ti.imageFileKey, sp.speechFileKey, sp.createdAt)
        FROM Stock st
        JOIN st.todayImages ti
        JOIN st.speeches sp
        WHERE FUNCTION('DATE', ti.createdAt) = FUNCTION('DATE', sp.createdAt)
        AND FUNCTION('DATE', ti.createdAt) BETWEEN :twoDaysAgo AND :now
        AND FUNCTION('DATE', sp.createdAt) BETWEEN :twoDaysAgo AND :now
        ORDER BY sp.createdAt DESC
        """
    )
    List<StockImageSpeechQueryDto> findStocksWithImagesAndSpeechesBetweenThreeDays(LocalDate twoDaysAgo, LocalDate now);

    @Query(
        """
        SELECT st
        FROM Stock st
        JOIN FETCH st.todayImages ti
        JOIN FETCH st.indicators ii
        JOIN FETCH st.recommends rc
        JOIN FETCH st.reports re
        JOIN FETCH st.speeches sp
        JOIN FETCH st.newsBriefings nb
        WHERE st.id = :stockId
        AND FUNCTION('DATE', ti.createdAt) = :selectedDate
        AND FUNCTION('DATE', ii.createdAt) = :selectedDate
        AND FUNCTION('DATE', rc.createdAt) = :selectedDate
        AND FUNCTION('DATE', re.createdAt) = :selectedDate
        AND FUNCTION('DATE', sp.createdAt) = :selectedDate
        AND FUNCTION('DATE', nb.createdAt) = :selectedDate
        """
    )
    StockDetailsRes findStockWithDetails(Long stockId, LocalDate selectedDate);

}

