package com.walker.aistock.domain.common.repository;

import com.walker.aistock.domain.common.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByTicker(String ticker);

}
