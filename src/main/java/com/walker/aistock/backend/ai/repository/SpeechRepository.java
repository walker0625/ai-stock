package com.walker.aistock.backend.ai.repository;

import com.walker.aistock.backend.ai.entity.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long> {
}
