package dev.changuii.project.controller;


import dev.changuii.project.service.ImageService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/sample")
public class SampleController {

    private ImageService imageService;

    public SampleController(
            @Autowired ImageService imageService
    ){
        this.imageService = imageService;
    }

    @PostMapping()
    public String testS3(
            @RequestPart("image")MultipartFile image
            ) throws IOException {


        return this.imageService.ImageUpload(image);
    }

    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] downloadImage(
            @RequestParam("imageUrl") String url
    ) throws IOException {
        return this.imageService.imageDownload(url);
    }




}
