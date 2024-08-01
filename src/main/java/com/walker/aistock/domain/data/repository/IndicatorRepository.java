package com.walker.aistock.domain.data.repository;

import com.walker.aistock.domain.data.entity.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
}
