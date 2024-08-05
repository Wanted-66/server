package dev.changuii.project.dto;


import dev.changuii.project.enums.FriendshipStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class FriendRequestAllowDto {

    Long friendshipId;
    FriendshipStatus friendshipStatus;

}
