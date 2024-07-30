package com.walker.aistock.domain.ai.dto.vo;

import com.walker.aistock.domain.common.enums.AskRole;
import lombok.Data;

@Data
public class Message {

    private AskRole role;
    private String content;

    public Message(AskRole role, String content) {
        this.role = role;
        this.content = content;
    }

}
