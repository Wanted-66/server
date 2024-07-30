package dev.changuii.project.service.impl;

import dev.changuii.project.dto.response.AuthResponseDto;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final JwtProvider jwtProvider;


    @Autowired
    public AuthServiceImpl(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    public AuthResponseDto login() {

        // need user validation

        return AuthResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .message("login successful")
                .refreshToken(jwtProvider.createRefreshToken("need user PK"))
                .accessToken(jwtProvider.createAccessToken("need user PK"))
                .build();
    }

    @Override
    public AuthResponseDto signin() {

        // need user validation

        return AuthResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .message("login successful")
                .refreshToken(jwtProvider.createRefreshToken("need user PK"))
                .accessToken(jwtProvider.createAccessToken("need user PK"))
                .build();
    }

    @Override
    public AuthResponseDto reissueAccessToken() {

        // need user validation

        return AuthResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .message("REISSUE ACCESS TOKEN")
                .refreshToken("")
                .accessToken(jwtProvider.createAccessToken("need user PK"))
                .build();
    }

    @Override
    public AuthResponseDto reissueRefreshToken() {

        // need user validation

        return AuthResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .message("REISSUE REFRESH TOKEN")
                .refreshToken(jwtProvider.createRefreshToken("need user PK"))
                .accessToken(jwtProvider.createAccessToken("need user PK"))
                .build();
    }

}
