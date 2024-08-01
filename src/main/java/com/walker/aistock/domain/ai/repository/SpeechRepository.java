package com.walker.aistock.domain.ai.repository;

import com.walker.aistock.domain.ai.entity.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long> {
}
