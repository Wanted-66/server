package dev.changuii.project.controller;

import dev.changuii.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(
            @Autowired ImageService imageService
    ){
        this.imageService = imageService;
    }

    @GetMapping(value = "/{imageName}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> imageDownload(
            @PathVariable("imageName") String image
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.imageService.imageDownload(image));
    }


}
