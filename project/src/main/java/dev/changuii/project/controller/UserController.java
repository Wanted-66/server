package dev.changuii.project.controller;


import dev.changuii.project.dto.response.ResponseDTO;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserSerivce userSerivce;
    private final JwtProvider jwtProvider;

    public UserController(
            @Autowired UserSerivce userSerivce,
            @Autowired JwtProvider jwtProvider
    ){
        this.userSerivce=userSerivce;
        this.jwtProvider=jwtProvider;
    }

    @PatchMapping("/image")
    public ResponseEntity<ResponseDTO<String>> updateUserProfileImage(
            @RequestPart("image")MultipartFile image
            ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }



}
