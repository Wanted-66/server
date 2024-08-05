package dev.changuii.project.controller;


import dev.changuii.project.dto.response.ResponseDTO;
import dev.changuii.project.enums.UserDesignation;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.IdempotentService;
import dev.changuii.project.service.UserSerivce;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserSerivce userSerivce;
    private final JwtProvider jwtProvider;
    private final IdempotentService idempotentService;
    private final String NAME = "USER";

    public UserController(
            @Autowired UserSerivce userSerivce,
            @Autowired JwtProvider jwtProvider,
            @Autowired IdempotentService idempotentService
    ){
        this.userSerivce=userSerivce;
        this.jwtProvider=jwtProvider;
        this.idempotentService=idempotentService;
    }

    private String extractUserEmail(HttpServletRequest request){
        return jwtProvider.getEmail(
                jwtProvider.getToken(request));
    }


    @PatchMapping("/image")
    public ResponseEntity<ResponseDTO<String>> updateUserProfileImage(
            @RequestPart("image")MultipartFile image,
            HttpServletRequest request
            ) throws IOException {
        String email = this.extractUserEmail(request);
        this.idempotentService.isValidIdempotent(Arrays.asList(NAME, "PATCH_IMAGE", email));
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userSerivce.changeUserProfileImage(email, image));
    }

    @GetMapping("/designation")
    public ResponseEntity<ResponseDTO<List<UserDesignation>>> readUserDesignationAll(
            HttpServletRequest request
    ){
        String email = this.extractUserEmail(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userSerivce.readUserDesignation(email));
    }

    @PatchMapping("/designation/{designation}")
    public ResponseEntity<ResponseDTO<UserDesignation>> changeDesignation(
            @PathVariable("designation") UserDesignation designation,
            HttpServletRequest request
    ){
        String email = this.extractUserEmail(request);
        this.idempotentService.isValidIdempotent(Arrays.asList(NAME, "PATCH_DESIGNATION", email));
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userSerivce.changeDesignation(email, designation));
    }

    @PostMapping("/designation/{designation}")
    public ResponseEntity<ResponseDTO<UserDesignation>> addDesignation(
            @PathVariable("designation") UserDesignation designation,
            HttpServletRequest request
    ){
        String email = this.extractUserEmail(request);
        this.idempotentService.isValidIdempotent(Arrays.asList(NAME, "POST_DESIGNATION", email));
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userSerivce.addDesignation(email, designation));

    }



}
