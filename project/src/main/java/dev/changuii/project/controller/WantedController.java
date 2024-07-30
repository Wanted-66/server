package dev.changuii.project.controller;


import dev.changuii.project.dto.WantedDTO;
import dev.changuii.project.dto.response.WantedResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/wanted")
public class WantedController {




    @PostMapping("/image")
    public ResponseEntity<WantedResponseDTO> createPostWithImage(
            @RequestPart("data") WantedDTO wantedDTO,
            @RequestPart("image")MultipartFile image
            ){
        return null;
    }

    @PostMapping("/")
    public ResponseEntity createPost(
            @RequestBody WantedDTO wantedDTO
    ){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity readPostById(
            @PathVariable("id") Long id
    ){
        return null;
    }





}
