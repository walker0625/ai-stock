package com.walker.aistock.domain.data.controller;

import com.walker.aistock.domain.data.service.FearGreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/fear-greed"))
public class FearGreedController {

    private final FearGreedService fearGreedService;

    @GetMapping("/test")
    public String test() {
        return fearGreedService.fearGreed().toString();
    }

}
