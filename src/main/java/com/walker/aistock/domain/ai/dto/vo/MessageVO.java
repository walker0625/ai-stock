package com.walker.aistock.domain.ai.dto.vo;

import com.walker.aistock.domain.common.enums.AskRole;
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
