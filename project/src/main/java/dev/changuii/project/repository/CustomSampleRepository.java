package dev.changuii.project.repository;

import dev.changuii.project.dto.SearchUserDto;
import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.dto.response.FriendRequestResponseDto;
import dev.changuii.project.entity.SampleEntity;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CustomSampleRepository {

    public SampleEntity SampleQuery();

    List<FriendListResponseDto> friendList(String email);
    List<FriendRequestResponseDto> friendRequestList(String email);
    Slice<FriendListResponseDto> findUserByNickname(SearchUserDto searchUserDto);

}
