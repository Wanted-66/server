package dev.changuii.project.service.impl;

import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.FriendshipRepository;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.service.FriendshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FriendshipServiceImpl implements FriendshipService {

    static final Logger log = LoggerFactory.getLogger(FriendshipServiceImpl.class);

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;


    public FriendshipServiceImpl(@Autowired FriendshipRepository friendshipRepository,
                                 @Autowired UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<FriendListResponseDto> getFriends(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return friendshipRepository.FriendList(userId);
    }
}
