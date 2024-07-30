package com.walker.aistock.domain.ai.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walker.aistock.domain.common.enums.SpeechModel;
import com.walker.aistock.domain.common.enums.SpeechResponseFormat;
import com.walker.aistock.domain.common.enums.Voice;
import lombok.Data;

@Data
public class ChatGPTSpeechReq {

    SpeechModel model = SpeechModel.TTS1;

    String input;

    Voice voice = Voice.NOVA;

    @JsonProperty("response_format")
    SpeechResponseFormat speechResponseFormat = SpeechResponseFormat.OPUS;

}
