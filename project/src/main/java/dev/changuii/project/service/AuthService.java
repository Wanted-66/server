package dev.changuii.project.service;

import dev.changuii.project.dto.response.AuthResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponseDto login();
    AuthResponseDto signin();
    AuthResponseDto reissueAccessToken(String nickname);
    AuthResponseDto reissueRefreshToken(String nickname);
}
