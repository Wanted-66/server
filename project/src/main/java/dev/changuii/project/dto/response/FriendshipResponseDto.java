package dev.changuii.project.dto.response;

import dev.changuii.project.enums.UserDesignation;
import lombok.*;

import java.time.LocalDate;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class FriendshipResponseDto {

    Long friendshipId;

    Long id;
    String name;
    String nickname;
    String profileImage;
    String introduction;
    LocalDate registerDate;
    UserDesignation designation;
}
