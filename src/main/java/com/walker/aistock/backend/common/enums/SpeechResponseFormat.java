package com.walker.aistock.backend.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*

MP3

브라우저 지원: 거의 모든 브라우저에서 지원
파일 크기: 보통, 중간 정도 (MP3는 오디오 파일을 손실 압축하는 형식이지만, Opus보다 덜 효율적)

Opus

브라우저 지원: 최신 브라우저에서 광범위하게 지원 (Chrome, Firefox, Edge, Opera 등)
파일 크기: 매우 작음 (Opus는 높은 효율성으로 알려져 있으며, 동일한 비트레이트에서 다른 형식보다 더 나은 품질을 제공합니다)

AAC

브라우저 지원: 대부분의 브라우저에서 지원 (특히 Apple 제품군)
파일 크기: 작음 (AAC는 MP3보다 더 효율적인 압축을 제공합니다)

FLAC

브라우저 지원: 일부 브라우저에서 지원 (특히 고음질 오디오 재생용)
파일 크기: 큼 (FLAC는 무손실 압축 형식으로, 파일 크기가 크지만 원본 음질을 유지)

WAV

브라우저 지원: 거의 모든 브라우저에서 지원
파일 크기: 매우 큼 (WAV는 비압축 형식으로, 원본 음질을 유지하지만 파일 크기가 매우 큼)

PCM

브라우저 지원: PCM 데이터는 일반적으로 WAV 형식 내에서 사용됩니다
파일 크기: 매우 큼 (비압축 형식, 원본 음질을 유지하지만 파일 크기가 매우 큼)

 */
@Getter
@AllArgsConstructor
public enum SpeechResponseFormat {

    MP3("mp3"),
    OPUS("opus"),
    AAC("aac"),
    FLAC("flac"),
    WAV("wav"),
    PCM("pcm")
    ;

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }

}