package com.walker.aistock.backend.common.repository;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.front.dto.res.StockImageSpeechQueryDto;
import com.walker.aistock.front.dto.res.StockDetailsRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// TODO 전체적으로 좀 더 가독성있고 효율적으로 리팩토링 요망
@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, StockCustomRepository {

    Stock findByTicker(String ticker);

    List<Stock> findAllByOrderByIdDesc();

    @Query(
        """
        SELECT new com.walker.aistock.front.dto.res.StockImageSpeechQueryDto(st.id, st.name, st.ticker, ti.imageFileKey, sp.speechFileKey, sp.createdAt)
        FROM Stock st
        JOIN st.todayImages ti
        JOIN st.speeches sp
        WHERE FUNCTION('DATE', ti.createdAt) = FUNCTION('DATE', sp.createdAt)
        AND FUNCTION('DATE', ti.createdAt) BETWEEN :twoDaysAgo AND :today
        AND FUNCTION('DATE', sp.createdAt) BETWEEN :twoDaysAgo AND :today
        ORDER BY sp.createdAt DESC
        """
    )
    List<StockImageSpeechQueryDto> findStocksWithImagesAndSpeechesBetweenThreeDays(LocalDate twoDaysAgo, LocalDate today);

    // index 활용을 위해 Date 함수를 Between으로 변경
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
        AND ti.createdAt BETWEEN :startDate AND :endDate
        AND ii.createdAt BETWEEN :startDate AND :endDate
        AND rc.createdAt BETWEEN :startDate AND :endDate
        AND re.createdAt BETWEEN :startDate AND :endDate
        AND sp.createdAt BETWEEN :startDate AND :endDate
        AND nb.createdAt BETWEEN :startDate AND :endDate
        """
    )
    StockDetailsRes findStockWithDetails(Long stockId, LocalDateTime startDate, LocalDateTime endDate);

}