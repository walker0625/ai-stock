package com.walker.aistock.domain.ai.dto.res;

import com.walker.aistock.domain.ai.vo.ImageData;
import lombok.Data;

import java.util.List;

@Data
public class ChatGPTImageRes {

    List<ImageData> data;

}
