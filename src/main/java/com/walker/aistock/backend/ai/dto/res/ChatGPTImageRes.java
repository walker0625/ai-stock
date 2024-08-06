package com.walker.aistock.backend.ai.dto.res;

import com.walker.aistock.backend.ai.vo.ImageData;
import lombok.Data;

import java.util.List;

@Data
public class ChatGPTImageRes {

    List<ImageData> data;

}
