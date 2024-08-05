package dev.changuii.project.dto.response;

import dev.changuii.project.enums.UserDesignation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class FriendRequestResponseDto {

    Long friendshipId;

    Long id;
    String name;
    String nickname;
    String profileImage;
    String introduction;
    LocalDate registerDate;
    UserDesignation designation;
}
