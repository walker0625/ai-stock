package com.walker.aistock.backend.ai.repository;

import com.walker.aistock.backend.ai.entity.TodayImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayImageRepository extends JpaRepository<TodayImage, Long> {
}
