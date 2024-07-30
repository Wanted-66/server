package dev.changuii.project.controller;


import dev.changuii.project.dto.WantedDTO;
import dev.changuii.project.dto.response.WantedResponseDTO;
import dev.changuii.project.service.WantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/wanted")
public class WantedController {


    private final WantedService wantedService;

    public WantedController(
            @Autowired WantedService wantedService
    ){
        this.wantedService=wantedService;
    }


    @PostMapping("/image")
    public ResponseEntity<WantedResponseDTO> createPostWithImage(
            @RequestPart("data") WantedDTO wantedDTO,
            @RequestPart("image")MultipartFile image
            ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.wantedService.createWanted(wantedDTO, image));
    }

    @PostMapping("/")
    public ResponseEntity<WantedResponseDTO> createPost(
            @RequestBody WantedDTO wantedDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.wantedService.createWanted(wantedDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WantedResponseDTO> readPostById(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.wantedService.readWantedById(id));
    }





}
