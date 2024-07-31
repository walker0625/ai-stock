package com.walker.aistock.domain.ai.dto.res;

import com.walker.aistock.domain.ai.vo.ChoiceVO;
import lombok.Data;

import java.util.List;

@Data
public class ChatGPTAskRes {

    private List<ChoiceVO> choices;

}
