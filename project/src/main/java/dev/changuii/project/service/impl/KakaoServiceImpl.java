package dev.changuii.project.service.impl;

import dev.changuii.project.dto.response.KakaoTokenResponseDto;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.service.KakaoService;
import io.netty.handler.codec.http.HttpHeaderValues;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpHeaders;


@Slf4j
@Service
public class KakaoServiceImpl implements KakaoService {

    private final WebClient webClient;
    private final String TOKEN_URL = "https://kauth.kakao.com";
    private final UserRepository userRepository;
    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;


//    https://kauth.kakao.com/oauth/authorize
//    https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}
//
//    https://kauth.kakao.com/oauth/token


    public KakaoServiceImpl(@Autowired WebClient.Builder webClientBuilder, UserRepository userRepository) {
        this.webClient = webClientBuilder.baseUrl("https://kauth.kakao.com").build();
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public KakaoTokenResponseDto getKakaoAccessToken(String code) {

        KakaoTokenResponseDto kakaoTokenResponseDto = webClient.post()
                .uri(uriBuilder -> uriBuilder
//                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", kakaoClientId)
                        .queryParam("code", code)
                        .build(true))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();


        assert kakaoTokenResponseDto != null;
        String userEmail = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/user/me")
                        .queryParam("property_keys", "kakao_account.email")
                        .build(true))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization" , "Bearer " + kakaoTokenResponseDto.getAccessToken())
                .retrieve().bodyToMono(String.class)
                .block();


        try {
            userRepository.findByEmail(userEmail)
                    .orElseThrow(()-> new CustomException(ErrorCode.DUPLICATION_EMAIL_EXCEPTION));
        }
        catch (CustomException e)
        {
            UserEntity.builder()
                    .email(kakaoTokenResponseDto.getEmail())
                    .build();

            userRepository.save(
                    UserEntity.builder()
                            .email(kakaoTokenResponseDto.getEmail())
                            .build());
        }

        kakaoTokenResponseDto.setEmail(userEmail);

        return kakaoTokenResponseDto;
    }
}
