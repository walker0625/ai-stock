package com.walker.aistock.domain.common.service;

import org.springframework.stereotype.Service;

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

    public String saveBase64Image(String base64) {

        String imageId = UUID.randomUUID().toString();
        String path = String.format(IMAGE_PATH.getAbsolutePath(), imageId, JPG.getValue());
        byte[] image = Base64.getDecoder().decode(base64);

        saveFile(path, image);

        return imageId;
    }


    public String saveSpeechAudio(byte[] speech) {

        String speechId = UUID.randomUUID().toString();
        String path = String.format(SPEECH_PATH.getAbsolutePath(), speechId, OPUS.getValue());

        saveFile(path, speech);

        return speechId;
    }

    public void saveFile(String path, byte[] file) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}