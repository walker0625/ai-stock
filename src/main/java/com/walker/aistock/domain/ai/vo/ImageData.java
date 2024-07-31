package com.walker.aistock.domain.ai.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageData {

    @JsonProperty("b64_json")
    private String base64;
    private String url; // 현재는 base64로만 받아옴(url은 1시간 후 비활성화됨)

    /* TODO 추후 활용여지가 있으면 사용
     ex : Create an illustration of cute and witty characters symbolizing the situation of stock market. One character is looking sad, embodying the declining shares of a major tech company associated with artificial intelligence. Another character bouncing happily could represent a semiconductor company with surprisingly strong quarterly results. Last but not least, draw a small yet fast-growing character winning a race, symbolizing small businesses growing faster than the tech-heavy stock index.
     */
    @JsonProperty("revised_prompt")
    private String revisedPrompt;

}
