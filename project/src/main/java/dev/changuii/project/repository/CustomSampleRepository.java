package dev.changuii.project.repository;

import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.entity.SampleEntity;

import java.util.List;

public interface CustomSampleRepository {

    public SampleEntity SampleQuery();

    List<FriendListResponseDto> FriendList(Long userId);
}
