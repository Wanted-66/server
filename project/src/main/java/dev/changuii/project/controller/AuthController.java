package dev.changuii.project.controller;


import dev.changuii.project.dto.response.AuthResponseDto;
import dev.changuii.project.dto.response.KakaoTokenResponseDto;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.AuthService;
import dev.changuii.project.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final KakaoService kakaoService;
    private final JwtProvider jwtProvider;



    public AuthController(@Autowired AuthService authService,
                          @Autowired KakaoService kakaoService,
                          @Autowired JwtProvider jwtProvider) {
        this.authService = authService;
        this.kakaoService = kakaoService;
        this.jwtProvider = jwtProvider;
    }

    // kakao auth callback
    @GetMapping("/callback")
    public ResponseEntity<AuthResponseDto> callback(@RequestParam("code") String code) {

        KakaoTokenResponseDto kakaoAUth = kakaoService.getKakaoAccessToken(code);

        AuthResponseDto responseDto = AuthResponseDto.builder()
                .accessToken(jwtProvider.createAccessToken(kakaoAUth.getEmail()))
                .refreshToken(jwtProvider.createRefreshToken(kakaoAUth.getEmail()))
                .build();

        return ResponseEntity.ok(responseDto);
    }

    //issue refresh token
    @PostMapping("/issue/access")
    public AuthResponseDto reissueRefreshToken(HttpServletRequest request){
        return authService.reissueRefreshToken(jwtProvider.getNickname(request));
    }


    //issue access token
    @PostMapping("/issue/refresh")
    public AuthResponseDto reissueAccessToken(HttpServletRequest request){
        return authService.reissueAccessToken(jwtProvider.getNickname(request));
    }

}
