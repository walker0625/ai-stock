package com.walker.aistock.backend.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

import static com.walker.aistock.backend.common.enums.FileType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.realpath.image}")
    String IMAGE_REAL_PATH;

    @Value("${file.realpath.speech}")
    private String SPEECH_REAL_PATH;

    public void saveBase64Image(String imageFileKey, String base64) {

        String path = IMAGE_REAL_PATH + imageFileKey + JPG.getValue();
        byte[] image = Base64.getDecoder().decode(base64);

        saveFile(path, image);
    }

    public void saveSpeechAudio(String speechFileKey, byte[] speech) {

        String path = SPEECH_REAL_PATH + speechFileKey + MP3.getValue();

        saveFile(path, speech);
    }

    public void saveFile(String path, byte[] file) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(file);
        } catch (IOException e) {
            deleteFile(path);
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String path) {
        File deleteFile = new File(path);
        deleteFile.delete();
    }

}