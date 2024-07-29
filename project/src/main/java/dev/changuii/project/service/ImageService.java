package dev.changuii.project.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {


    public String ImageUpload(MultipartFile image) throws IOException;

    public byte[] imageDownload(String data) throws IOException;
}
