package com.walker.aistock.domain.ai.dto.req;

import com.walker.aistock.domain.ai.dto.vo.MessageVO;
import com.walker.aistock.domain.common.enums.AskModel;
import lombok.Data;

import java.util.List;

@Data
public class ChatGPTAskReq {

    private AskModel model = AskModel.GPT4_TURBO;
    private List<MessageVO> messages;

    public ChatGPTAskReq(List<MessageVO> messages) {
        this.messages = messages;
    }

}
