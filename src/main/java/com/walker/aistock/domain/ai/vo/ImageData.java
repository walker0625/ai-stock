package com.walker.aistock.domain.ai.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageData {

    @JsonProperty("b64_json")
    private String base64;
    private String url;

    @JsonProperty("revised_prompt")
    private String revisedPrompt;

}
