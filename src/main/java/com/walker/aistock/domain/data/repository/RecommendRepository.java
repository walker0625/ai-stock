package com.walker.aistock.domain.data.repository;

import com.walker.aistock.domain.data.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {

    Recommend findByStockIdAndRecommendDate(Long id, String period);

}
