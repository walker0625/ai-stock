package com.walker.aistock.backend.ai.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGPTSpeechRes {

    private String fileKey;

    private byte[] file;

}
