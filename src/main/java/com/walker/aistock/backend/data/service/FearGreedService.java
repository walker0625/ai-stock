package com.walker.aistock.backend.data.service;

import com.walker.aistock.backend.common.service.WebClientService;
import com.walker.aistock.backend.data.dto.res.FearGreedRes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FearGreedService {

    WebClientService webClientService;

    // TODO 추후 rapid api 비용이 과도하게 나오면 셀레니움(크롬 드라이버 필요)으로 직접 스크랩핑 고려
    public FearGreedRes fearGreed() {
        return webClientService.fearGreed();
    }

}
