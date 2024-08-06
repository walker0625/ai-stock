package com.walker.aistock.backend.ai.repository;

import com.walker.aistock.backend.ai.entity.NewsBriefing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsBriefingRepository extends JpaRepository<NewsBriefing, Long> {
}
