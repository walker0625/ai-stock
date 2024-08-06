package com.walker.aistock.front.service;

import com.walker.aistock.backend.common.repository.StockRepository;
import com.walker.aistock.front.dto.res.StockImageSpeechRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.internal.Collections;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PresentationService {

    StockRepository stockRepository;

    public List<StockImageSpeechRes> stockWithImageAndSpeech() {
        return stockRepository.findStocksWithImagesAndSpeechesBetweenThreeDays(LocalDate.now().minusDays(2), LocalDate.now())
                              .stream().map(StockImageSpeechRes::new).collect(Collectors.toList());
    }

}
