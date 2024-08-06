package com.walker.aistock.front.controller;

import com.walker.aistock.backend.common.entity.Stock;
import com.walker.aistock.backend.common.repository.StockRepository;
import com.walker.aistock.backend.common.service.FileService;
import com.walker.aistock.front.service.PresentationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PresentationController {

    StockRepository stockRepository;

    @RequestMapping("/main")
    public String home(Model model) {

        model.addAttribute("stocks", presentationService.stockWithImageAndSpeech());

        return "/main";
    }

    PresentationService presentationService;

    @RequestMapping("/stocks/{ticker}")
    public String stock(@PathVariable String ticker) {

        return "/stock";
    }

}
