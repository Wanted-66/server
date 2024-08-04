package dev.changuii.project.service.impl;

import dev.changuii.project.dto.response.KakaoTokenResponseDto;
import dev.changuii.project.service.KakaoService;
import io.netty.handler.codec.http.HttpHeaderValues;
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
    @Value("${kakao.client_id}")
    private String kakaoClientId;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;


//    https://kauth.kakao.com/oauth/authorize
//    https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}
//
//    https://kauth.kakao.com/oauth/token


    public KakaoServiceImpl(@Autowired WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://kauth.kakao.com").build();
    }


    @Override
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




        log.info(" [Kakao Service] Access Token \n {}", kakaoTokenResponseDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token \n {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info(" [Kakao Service] Id Token \n {}", kakaoTokenResponseDto.getIdToken());
        log.info(" [Kakao Service] Scope \n {}", kakaoTokenResponseDto.getScope());


        return kakaoTokenResponseDto;
    }
}
