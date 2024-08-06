package com.walker.aistock.backend.ai.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walker.aistock.backend.common.enums.ImageModel;
import com.walker.aistock.backend.common.enums.ImageQuality;
import com.walker.aistock.backend.common.enums.ImageResponseFormat;
import com.walker.aistock.backend.common.enums.ImageSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTImageReq {

    private ImageModel model = ImageModel.DALLE3;

    @JsonProperty("response_format")
    private ImageResponseFormat responseFormat = ImageResponseFormat.BASE64;

    private String prompt;

    private ImageSize size = ImageSize.DALLE3_MEDIUM;

    private ImageQuality quality = ImageQuality.STANDARD;

    private int n = 1; // dall-e-3 은 2024/07/23 기준 1장만 지원(2는 1~10장)

    public ChatGPTImageReq(String prompt) {
        this.prompt = prompt;
    }

}
