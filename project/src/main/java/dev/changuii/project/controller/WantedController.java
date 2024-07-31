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
import java.util.List;

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
    public ResponseEntity<WantedResponseDTO> createWantedWithImage(
            @RequestPart("dto") WantedDTO wantedDTO,
            @RequestPart("main") MultipartFile mainImage,
            @RequestPart("signature") MultipartFile signature
            ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.wantedService.createWanted(wantedDTO, mainImage, signature));
    }

    @PostMapping()
    public ResponseEntity<WantedResponseDTO> createWanted(
            @RequestPart("dto") WantedDTO wantedDTO,
            @RequestPart("signature") MultipartFile signature
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.wantedService.createWanted(wantedDTO, signature));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WantedResponseDTO> readWantedById(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.wantedService.readWantedById(id));
    }

    @GetMapping("/all/{email}")
    public ResponseEntity<List<WantedResponseDTO>> readAllWantedByUserEmail(
            @PathVariable("email") String email
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.wantedService.readAllWantedByEmail(email));
    }





}
