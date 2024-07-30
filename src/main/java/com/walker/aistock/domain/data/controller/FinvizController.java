package com.walker.aistock.domain.data.controller;

import com.walker.aistock.domain.data.service.FinvizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(("/finviz"))
public class FinvizController {

    private final FinvizService finvizService;

    @GetMapping("/test")
    public String test() throws IOException {
        return finvizService.scrapingFinviz("NVDA").toString();
    }

}
