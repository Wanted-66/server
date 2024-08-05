package dev.changuii.project.service;

import dev.changuii.project.dto.FriendRequestAllowDto;
import dev.changuii.project.dto.FriendRequestDto;
import dev.changuii.project.dto.response.FriendshipResponseDto;

import java.util.List;

public interface FriendshipService {

    List<FriendshipResponseDto> getFriends(String nickname);
    List<FriendshipResponseDto> getFriendRequestList(String email);
    List<FriendshipResponseDto> findUserByNickname(String email, String nickname);
    void friendRequest(String token, FriendRequestDto friendRequestDto);
    void friendRequestAllow(FriendRequestAllowDto friendRequestAllowDto);
}
