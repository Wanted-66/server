package dev.changuii.project.service;

import dev.changuii.project.dto.response.KakaoTokenResponseDto;
import reactor.core.publisher.Mono;

public interface KakaoService {

    public KakaoTokenResponseDto getKakaoAccessToken(String code);
}
