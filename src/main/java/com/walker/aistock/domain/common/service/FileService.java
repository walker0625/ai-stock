package com.walker.aistock.domain.common.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import static com.walker.aistock.domain.common.enums.FileType.*;
import static com.walker.aistock.domain.common.enums.FilePath.IMAGE_PATH;
import static com.walker.aistock.domain.common.enums.FilePath.SPEECH_PATH;

@Service
public class FileService {

    public void saveBase64Image(String fileKey, String base64) {

        String path = String.format(IMAGE_PATH.getAbsolutePath(), fileKey, JPG.getValue());
        byte[] image = Base64.getDecoder().decode(base64);

        saveFile(path, image);
    }

    public void saveSpeechAudio(String fileKey, byte[] speech) {

        String path = String.format(SPEECH_PATH.getAbsolutePath(), fileKey, OPUS.getValue());

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