package com.walker.aistock.backend.ai.repository;

import com.walker.aistock.backend.ai.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
