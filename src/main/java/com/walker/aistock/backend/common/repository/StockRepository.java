package com.walker.aistock.backend.common.repository;

import com.walker.aistock.backend.common.entity.Stock;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, StockCustomRepository {

    Stock findByTicker(String ticker);

    @EntityGraph(attributePaths = {"todayImages", "speeches"})
    @Query("SELECT st " +
            "FROM Stock st " +
            "JOIN st.todayImages ti " +
            "JOIN st.speeches sp " +
            "WHERE FUNCTION('DATE', ti.createdAt) BETWEEN :twoDaysAgo AND :now ")
    List<Stock> findStocksWithImagesAndSpeechesBetweenThreeDays(LocalDate twoDaysAgo, LocalDate now);
}
