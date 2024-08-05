package dev.changuii.project.service;

import dev.changuii.project.dto.FriendRequestAllowDto;
import dev.changuii.project.dto.FriendRequestDto;
import dev.changuii.project.dto.SearchUserDto;
import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.dto.response.FriendRequestResponseDto;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FriendshipService {

    List<FriendListResponseDto> getFriends(String nickname);
    List<FriendRequestResponseDto> getFriendRequestList(String email);
    Slice<FriendListResponseDto> findUserByNickname(SearchUserDto searchUserDto);
    void friendRequest(String token, FriendRequestDto friendRequestDto);
    void friendRequestAllow(FriendRequestAllowDto friendRequestAllowDto);
}
