package com.walker.aistock.backend.ai.dto.res;

import com.walker.aistock.backend.ai.vo.ChoiceVO;
import lombok.Data;

import java.util.List;

@Data
public class ChatGPTAskRes {

    private List<ChoiceVO> choices;

}
