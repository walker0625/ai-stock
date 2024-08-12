package com.walker.aistock.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePath {

    // PATH / ID . FILETYPE
    IMAGE_SRC_PATH("/ai/image/"),
    SPEECH_SRC_PATH("/ai/speech/"),
    ;

    private final String value;

}