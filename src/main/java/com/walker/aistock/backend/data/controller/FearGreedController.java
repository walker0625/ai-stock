package com.walker.aistock.backend.data.controller;

import com.walker.aistock.backend.data.service.FearGreedService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/fear-greed"))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FearGreedController {

    private final FearGreedService fearGreedService;

    @GetMapping("/test")
    public String test() {
        return fearGreedService.fearGreed().toString();
    }

}
