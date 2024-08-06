package com.walker.aistock.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePath {

    // PATH / ID . FILETYPE
    IMAGE_SRC_PATH("/ai/image/%s.%s"),
    SPEECH_SRC_PATH("/ai/speech/%s.%s"),

    // PATH / ID . FILETYPE
    IMAGE_REAL_PATH("/Users/minwoojeon/J/AI-STOCK/IMAGE/%s.%s"),
    SPEECH_REAL_PATH("/Users/minwoojeon/J/AI-STOCK/SPEECH/%s.%s")
    ;

    private final String value;

}