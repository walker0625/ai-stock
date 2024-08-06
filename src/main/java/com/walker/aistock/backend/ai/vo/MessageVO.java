package com.walker.aistock.backend.ai.vo;

import com.walker.aistock.backend.common.enums.AskRole;
import lombok.Data;

@Data
public class MessageVO {

    private AskRole role;
    private String content;

    public MessageVO(AskRole role, String content) {
        this.role = role;
        this.content = content;
    }

}
