package com.walker.aistock.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Path {

    // PATH/ID.FILETYPE
    IMAGE_PATH("/Users/minwoojeon/J/AI-STOCK/IMAGE/%s.%s"),
    SPEECH_PATH("/Users/minwoojeon/J/AI-STOCK/SPEECH/%s.%s")
    ;

    private final String absolutePath;

}