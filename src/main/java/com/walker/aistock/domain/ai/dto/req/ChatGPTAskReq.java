package com.walker.aistock.domain.ai.dto.req;

import com.walker.aistock.domain.ai.vo.MessageVO;
import com.walker.aistock.domain.common.enums.AskModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTAskReq {

    private AskModel model = AskModel.GPT4_TURBO;
    private List<MessageVO> messages;

    public ChatGPTAskReq(List<MessageVO> messages) {
        this.messages = messages;
    }

}
