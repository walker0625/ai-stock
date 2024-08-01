package com.walker.aistock.domain.ai.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walker.aistock.domain.common.enums.SpeechModel;
import com.walker.aistock.domain.common.enums.SpeechResponseFormat;
import com.walker.aistock.domain.common.enums.SpeechVoice;
import lombok.Data;

@Data
public class ChatGPTSpeechReq {

    SpeechModel model = SpeechModel.TTS1;

    String input;

    SpeechVoice voice = SpeechVoice.NOVA;

    @JsonProperty("response_format")
    SpeechResponseFormat speechResponseFormat = SpeechResponseFormat.OPUS;

    public ChatGPTSpeechReq(String input) {
        this.input = input;
    }

}