package com.walker.aistock.domain.ai.repository;

import com.walker.aistock.domain.ai.entity.NewsBriefing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsBriefingRepository extends JpaRepository<NewsBriefing, Long> {
}
