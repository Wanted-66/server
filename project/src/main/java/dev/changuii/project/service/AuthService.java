package dev.changuii.project.service;

import dev.changuii.project.dto.AuthResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponseDto login();
    AuthResponseDto signin();
    AuthResponseDto reissueAccessToken();
    AuthResponseDto reissueRefreshToken();
}
