package com.walker.aistock.domain.ai.dto.req;

import com.walker.aistock.domain.ai.dto.vo.Message;
import lombok.Data;

import java.util.List;

@Data
public class ChatGPTAskReq {

    private String model;
    private List<Message> messages;

}
