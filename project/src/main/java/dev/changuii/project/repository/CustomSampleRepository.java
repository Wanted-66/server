package dev.changuii.project.repository;

import dev.changuii.project.dto.response.FriendshipResponseDto;
import dev.changuii.project.entity.SampleEntity;

import java.util.List;

public interface CustomSampleRepository {

    public SampleEntity SampleQuery();

    List<FriendshipResponseDto> friendList(String email);
    List<FriendshipResponseDto> friendRequestList(String email);
    List<FriendshipResponseDto> findUserByNickname(String email, String nickname);

}
