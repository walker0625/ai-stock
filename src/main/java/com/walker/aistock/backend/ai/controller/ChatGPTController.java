package com.walker.aistock.backend.ai.controller;

import com.walker.aistock.backend.ai.dto.req.ChatGPTAskReq;
import com.walker.aistock.backend.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.backend.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.backend.ai.dto.res.ChatGPTImageRes;
import com.walker.aistock.backend.ai.service.ChatGPTService;
import com.walker.aistock.backend.common.repository.StockRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatgpt")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatGPTController {

    ChatGPTService chatGPTService;

    StockRepository stockRepository;

    @GetMapping("/stock")
    public void addStock(String keyword) {
        chatGPTService.addStock(keyword);
    }

    @GetMapping("/analysis")
    public String chatGPTAnalysis(String ticker) {
        return chatGPTService.chatGPTAnalysis(stockRepository.findByTicker(ticker));
    }

    @PostMapping("/image")
    public ChatGPTImageRes chatGPTImage(@RequestBody ChatGPTImageReq chatGPTImageReq) {
        return chatGPTService.chatGPTImage(chatGPTImageReq);
    }

    @PostMapping("/speech")
    public byte[] chatGPTSpeech(@RequestBody ChatGPTSpeechReq chatGPTSpeechReq) {
        return chatGPTService.chatGPTSpeech(chatGPTSpeechReq);
    }

    @PostMapping("/text")
    public String chatGPTReport(@RequestBody ChatGPTAskReq chatGPTAskReq) {
        return chatGPTService.chatGPTText(chatGPTAskReq);
    }

}