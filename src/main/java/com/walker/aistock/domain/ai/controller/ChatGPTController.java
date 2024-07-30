package com.walker.aistock.domain.ai.controller;

import com.walker.aistock.domain.ai.dto.req.ChatGPTImageReq;
import com.walker.aistock.domain.ai.dto.req.ChatGPTSpeechReq;
import com.walker.aistock.domain.ai.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/chatgpt")
@RestController
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @GetMapping("/report")
    public String chatGPTReport(String keyword, String ticker) {
        return chatGPTService.chatGPTReport(keyword, ticker);
    }

    @PostMapping("/image")
    public String chatGPTImage(@RequestBody ChatGPTImageReq chatGPTImageReq) {
        return chatGPTService.chatGPTImage(chatGPTImageReq);
    }

    @PostMapping("/speech")
    public String chatGPTSpeech(@RequestBody ChatGPTSpeechReq chatGPTSpeechReq) {
        return chatGPTService.chatGPTSpeech(chatGPTSpeechReq);
    }

}