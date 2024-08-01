package com.walker.aistock.domain.ai.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGPTSpeechRes {

    private String fileKey;

    private byte[] file;

}
