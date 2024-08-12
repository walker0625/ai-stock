package com.walker.aistock.backend.ai.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walker.aistock.backend.common.enums.SpeechModel;
import com.walker.aistock.backend.common.enums.SpeechResponseFormat;
import com.walker.aistock.backend.common.enums.SpeechVoice;
import lombok.Data;

@Data
public class ChatGPTSpeechReq {

    SpeechModel model = SpeechModel.TTS1;

    String input;

    SpeechVoice voice = SpeechVoice.NOVA;

    @JsonProperty("response_format")
    SpeechResponseFormat speechResponseFormat = SpeechResponseFormat.MP3;

    public ChatGPTSpeechReq(String input) {
        this.input = input;
    }

}