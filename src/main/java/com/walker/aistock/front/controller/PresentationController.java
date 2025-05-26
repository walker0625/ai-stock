package com.walker.aistock.front.controller;

import com.walker.aistock.backend.common.service.AccessService;
import com.walker.aistock.front.service.PresentationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PresentationController {

    PresentationService presentationService;
    AccessService accessService;

    @GetMapping("/main")
    public String home(Model model,
                       HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");

        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        log.info("main access ip : {}", ip);

        accessService.checkIp(ip);

        //model.addAttribute("feargreed", presentationService.fearGreed());
        model.addAttribute("stocks", presentationService.stockWithImage());

        return "list";
    }

    @GetMapping("/stocks/{stockId}")
    public String stock(@PathVariable String stockId,
                        @RequestParam String date,
                        HttpServletRequest request,
                        Model model) {

        log.info("stock access ip : {}, stockId : {}, date : {}", request.getRemoteAddr(), stockId, date);

        model.addAttribute("stock", presentationService.stockWithDetails(stockId, date));
        model.addAttribute("newses", presentationService.stockWithNewses(stockId, date));

        return "stock";
    }

    @GetMapping("/principle")
    public String principle(Model model,
                            HttpServletRequest request) {

        log.info("principle access ip : {}", request.getRemoteAddr());

        model.addAttribute("principle", presentationService.principle());

        return "principle";
    }

}