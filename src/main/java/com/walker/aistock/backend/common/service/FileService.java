package com.walker.aistock.backend.common.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

import static com.walker.aistock.backend.common.enums.FileType.*;
import static com.walker.aistock.backend.common.enums.FilePath.IMAGE_REAL_PATH;
import static com.walker.aistock.backend.common.enums.FilePath.SPEECH_REAL_PATH;

@Service
public class FileService {

    public void saveBase64Image(String imageFileKey, String base64) {

        String path = String.format(IMAGE_REAL_PATH.getValue(), imageFileKey, JPG.getValue());
        byte[] image = Base64.getDecoder().decode(base64);

        saveFile(path, image);
    }

    public void saveSpeechAudio(String speechFileKey, byte[] speech) {

        String path = String.format(SPEECH_REAL_PATH.getValue(), speechFileKey, OPUS.getValue());

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