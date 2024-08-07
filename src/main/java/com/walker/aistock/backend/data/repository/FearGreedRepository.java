package com.walker.aistock.backend.data.repository;

import com.walker.aistock.backend.data.entity.FearGreed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FearGreedRepository extends JpaRepository<FearGreed, Long> {

    @Query("SELECT fg FROM FearGreed fg WHERE DATE(fg.createdAt) = CURRENT_DATE")
    FearGreed findByCreatedAtToday();

}
