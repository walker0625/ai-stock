package com.walker.aistock.backend.data.controller;

import com.walker.aistock.backend.data.service.FinvizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/finviz"))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FinvizController {

    FinvizService finvizService;

    @GetMapping("/test")
    public String test() throws IOException {
        return finvizService.scrapingFinviz("NVDA").toString();
    }

}
