package dev.changuii.project.service;

import dev.changuii.project.dto.response.FriendListResponseDto;

import java.util.List;

public interface FriendshipService {

    public List<FriendListResponseDto> getFriends(Long userId);


}
