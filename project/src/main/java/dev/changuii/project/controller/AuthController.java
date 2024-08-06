package dev.changuii.project.controller;


import dev.changuii.project.dto.UserSignInDto;
import dev.changuii.project.dto.response.AuthResponseDto;
import dev.changuii.project.dto.response.KakaoTokenResponseDto;
import dev.changuii.project.dto.response.ResponseDTO;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.AuthService;
import dev.changuii.project.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final KakaoService kakaoService;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;


    public AuthController(@Autowired AuthService authService,
                          @Autowired KakaoService kakaoService,
                          @Autowired JwtProvider jwtProvider,
                          @Autowired UserRepository userRepository) {
        this.authService = authService;
        this.kakaoService = kakaoService;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    // kakao auth callback
    @GetMapping("/callback")
    public ResponseEntity<AuthResponseDto> callback(@RequestParam("code") String code) {

        KakaoTokenResponseDto kakaoAuth = kakaoService.getKakaoAccessToken(code);

        AuthResponseDto responseDto = AuthResponseDto.builder()
                .message(kakaoAuth.getEmail())
                .accessToken(jwtProvider.createAccessToken(kakaoAuth.getEmail()))
                .refreshToken(jwtProvider.createRefreshToken(kakaoAuth.getEmail()))
                .build();

        return ResponseEntity.ok(responseDto);
    }

    //닉네임 중복체크
    @GetMapping("/check")
    public ResponseEntity<Void> checkDuplicate(String nickname)
    {
        userRepository.findByNickname(nickname)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        return ResponseEntity.ok().build();
    }


    //카카오 로그인 이후 추가 정보 받기
    @PatchMapping("/init")
    public ResponseEntity<Void> addUserInfo(@RequestBody UserSignInDto userSignInDto)
    {
        authService.updateUserInfo(userSignInDto);
        return ResponseEntity.ok().build();
    }


    //issue refresh token
    @GetMapping("/issue/access")
    public AuthResponseDto reissueRefreshToken(HttpServletRequest request){
        String token = jwtProvider.getToken(request);
        return authService.reissueRefreshToken(jwtProvider.getEmail(token));
    }


    //issue access token
    @GetMapping("/issue/refresh")
    public AuthResponseDto reissueAccessToken(HttpServletRequest request){
        String token = jwtProvider.getToken(request);
        return authService.reissueAccessToken(jwtProvider.getEmail(token));
    }

}
