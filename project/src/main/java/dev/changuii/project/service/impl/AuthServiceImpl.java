package dev.changuii.project.service.impl;

import dev.changuii.project.dto.UserSignInDto;
import dev.changuii.project.dto.response.AuthResponseDto;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;


    @Autowired
    public AuthServiceImpl(JwtProvider jwtProvider, UserRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }


    @Override
    public AuthResponseDto login() {

        // need user validation

        return AuthResponseDto.builder()
                .message("login successful")
                .refreshToken(jwtProvider.createRefreshToken("need user PK"))
                .accessToken(jwtProvider.createAccessToken("need user PK"))
                .build();
    }

    @Override
    public AuthResponseDto signin() {

        // need user validation

        return AuthResponseDto.builder()
                .message("login successful")
                .refreshToken(jwtProvider.createRefreshToken("need user PK"))
                .accessToken(jwtProvider.createAccessToken("need user PK"))
                .build();
    }

    @Override
    public AuthResponseDto reissueAccessToken(String email) {

        userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return AuthResponseDto.builder()
                .message("ISSUE ACCESS TOKEN")
                .refreshToken("")
                .accessToken(jwtProvider.createAccessToken(email))
                .build();
    }

    @Override
    public AuthResponseDto reissueRefreshToken(String email) {

        userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return AuthResponseDto.builder()
                .message("ISSUE REFRESH TOKEN")
                .refreshToken(jwtProvider.createRefreshToken(email))
                .accessToken(jwtProvider.createAccessToken(email))
                .build();
    }

    @Override
    @Transactional
    public void updateUserInfo(UserSignInDto userSignInDto) {

        UserEntity userEntity = userRepository.findByEmail(userSignInDto.getEmail())
                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        userEntity.initialUpdate(userSignInDto);
    }


}
