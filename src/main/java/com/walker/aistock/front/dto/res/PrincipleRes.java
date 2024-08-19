package com.walker.aistock.front.dto.res;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrincipleRes {

    String content;

    public PrincipleRes(String content) {
        this.content = content;
    }

}
