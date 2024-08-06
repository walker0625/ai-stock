package com.walker.aistock.backend.ai.dto.req;

import com.walker.aistock.backend.ai.vo.MessageVO;
import com.walker.aistock.backend.common.enums.AskModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTAskReq {

    private AskModel model = AskModel.GPT4_O;
    private List<MessageVO> messages;

    public ChatGPTAskReq(List<MessageVO> messages) {
        this.messages = messages;
    }

}
