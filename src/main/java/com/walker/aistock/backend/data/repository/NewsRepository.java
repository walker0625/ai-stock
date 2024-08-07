package com.walker.aistock.backend.data.repository;

import com.walker.aistock.backend.data.entity.News;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.stock.id = :stockId AND FUNCTION('DATE', n.createdAt) = :date")
    List<News> findByStockIdAndCreatedAt(Long stockId, LocalDate date, PageRequest limit);

}
