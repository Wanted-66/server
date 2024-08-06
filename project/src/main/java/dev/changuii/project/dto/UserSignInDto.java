package dev.changuii.project.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class UserSignInDto {

    private String name;
    private String phoneNum;
    private String email;
    private String profileImage;
    private LocalDate registerDate;
    private String introduction;
    private String nickname;
    private String bankAccount;
}
