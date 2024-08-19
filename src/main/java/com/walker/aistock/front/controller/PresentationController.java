package com.walker.aistock.front.controller;

import com.walker.aistock.front.service.PresentationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PresentationController {

    PresentationService presentationService;

    @RequestMapping("/main")
    public String home(Model model) {

        model.addAttribute("feargreed", presentationService.fearGreed());
        model.addAttribute("stocks", presentationService.stockWithImageAndSpeech());

        return "main";
    }

    @RequestMapping("/stocks/{stockId}")
    public String stock(@PathVariable String stockId,
                        @RequestParam String date,
                        Model model) {

        LocalDate selectedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        model.addAttribute("stock", presentationService.stockWithDetails(Long.parseLong(stockId), selectedDate));
        model.addAttribute("newses", presentationService.stockWithNewses(Long.parseLong(stockId), selectedDate));

        return "stock";
    }

    @RequestMapping("/principle")
    public String principal(Model model) {

        model.addAttribute("principle", presentationService.principle());

        return "principle";
    }

}
