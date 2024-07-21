package dev.changuii.project.controller;


import dev.changuii.project.dto.AuthResponseDto;
import dev.changuii.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //login
    @PostMapping("/signin")
    public AuthResponseDto login()
    {
        return authService.login();
    }

    //signup
    @PostMapping("/signup")
    public AuthResponseDto signin(){
        return authService.signin();
    }


    //issue refresh token
    @PostMapping("/issue/access")
    public AuthResponseDto reissueRefreshToken(){
        return authService.reissueRefreshToken();
    }


    //issue access token
    @PostMapping("/issue/refresh")
    public AuthResponseDto reissueAccessToken(){
        return authService.reissueAccessToken();
    }



}
