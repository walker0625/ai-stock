package com.walker.aistock.domain.ai.repository;

import com.walker.aistock.domain.ai.entity.TodayImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayImageRepository extends JpaRepository<TodayImage, Long> {
}
