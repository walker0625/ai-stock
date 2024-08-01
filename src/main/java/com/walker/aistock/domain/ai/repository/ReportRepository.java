package com.walker.aistock.domain.ai.repository;

import com.walker.aistock.domain.ai.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
