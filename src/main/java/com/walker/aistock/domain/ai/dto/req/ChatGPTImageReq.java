package com.walker.aistock.domain.ai.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walker.aistock.domain.common.enums.ImageModel;
import com.walker.aistock.domain.common.enums.ImageQuality;
import com.walker.aistock.domain.common.enums.ImageResponseFormat;
import com.walker.aistock.domain.common.enums.ImageSize;
import lombok.Data;

@Data
public class ChatGPTImageReq {

    private ImageModel model = ImageModel.DALLE2;

    @JsonProperty("response_format")
    private ImageResponseFormat responseFormat = ImageResponseFormat.BASE64;

    private String prompt;

    private ImageSize size = ImageSize.DALLE2_MEDIUM;

    private ImageQuality quality = ImageQuality.STANDARD;

    private int n = 1; // dall-e-3 은 2024/07/23 기준 1장만 지원(2는 1~10장)

}
