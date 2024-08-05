package dev.changuii.project.controller;


import dev.changuii.project.dto.WantedDTO;
import dev.changuii.project.dto.response.WantedResponseDTO;
import dev.changuii.project.service.IdempotentService;
import dev.changuii.project.service.WantedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/wanted")
public class WantedController {


    private final WantedService wantedService;
    private final IdempotentService idempotentService;
    private final String NAME = "WANTED";

    public WantedController(
            @Autowired IdempotentService idempotentService,
            @Autowired WantedService wantedService
    ){
        this.idempotentService=idempotentService;
        this.wantedService=wantedService;
    }


    // todo : 이메일 가져오는 로직 변경해야함

    @PostMapping("/image")
    public ResponseEntity<WantedResponseDTO> createWantedWithImage(
            @Valid @RequestPart("dto") WantedDTO wantedDTO,
            @RequestPart("main") MultipartFile mainImage,
            @RequestPart("signature") MultipartFile signature
            ) throws IOException {
        this.idempotentService.isValidIdempotent(Arrays.asList(new String[]{NAME, "POST", wantedDTO.getUserEmail()}));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.wantedService.createWanted(wantedDTO, mainImage, signature));
    }

    @PostMapping()
    public ResponseEntity<WantedResponseDTO> createWanted(
            @Valid @RequestPart("dto") WantedDTO wantedDTO,
            @RequestPart("signature") MultipartFile signature
    ) throws IOException {
        this.idempotentService.isValidIdempotent(Arrays.asList(new String[]{NAME, "POST", wantedDTO.getUserEmail()}));
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

    @GetMapping("/is-progress/{email}")
    public ResponseEntity<Boolean> isProgressUser(
            @PathVariable("email") String email
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.wantedService.isProgressByEmail(email));
    }


    @PatchMapping("/{id}/{status}")
    public ResponseEntity<WantedResponseDTO> modifyWantedStatus(
            @PathVariable("id") Long id,
            @PathVariable("status") String status
    ){
        this.idempotentService.isValidIdempotent(Arrays.asList(new String[]{NAME, "PATCH", id.toString()}));
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.wantedService.modifyWantedStatus(id, status));
    }






}
