package dev.changuii.project.dto;


import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
@Builder
public class AuthResponseDto {

    String status;
    String message;
    String accessToken;
    String refreshToken;
}
