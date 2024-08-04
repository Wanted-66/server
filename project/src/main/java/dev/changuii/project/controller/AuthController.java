package dev.changuii.project.controller;


import dev.changuii.project.dto.response.AuthResponseDto;
import dev.changuii.project.dto.response.KakaoTokenResponseDto;
import dev.changuii.project.service.AuthService;
import dev.changuii.project.service.KakaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final KakaoService kakaoService;


    public AuthController(@Autowired AuthService authService,
                          @Autowired KakaoService kakaoService) {
        this.authService = authService;
        this.kakaoService = kakaoService;
    }

    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    private final String base = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}";


    //kakao login
//    @GetMapping("/kakao")
//    @GetMapping()
//    public ResponseEntity<AuthResponseDto> getAuth() {
//
//
//    }



    //callback
    @GetMapping("/callback")
    public ResponseEntity<Void> callback(@RequestParam("code") String code) {
        log.info(code);
        KakaoTokenResponseDto accessToken = kakaoService.getKakaoAccessToken(code);
        log.info(accessToken.toString());
        return new ResponseEntity<>(HttpStatus.OK);
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
