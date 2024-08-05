package dev.changuii.project.service.impl;

import dev.changuii.project.dto.FriendRequestAllowDto;
import dev.changuii.project.dto.FriendRequestDto;
import dev.changuii.project.dto.SearchUserDto;
import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.dto.response.FriendRequestResponseDto;
import dev.changuii.project.entity.FriendshipEntity;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.enums.FriendshipStatus;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.FriendshipRepository;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.FriendshipService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FriendshipServiceImpl implements FriendshipService {

    static final Logger log = LoggerFactory.getLogger(FriendshipServiceImpl.class);

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    public FriendshipServiceImpl(@Autowired FriendshipRepository friendshipRepository,
                                 @Autowired UserRepository userRepository, JwtProvider jwtProvider) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public List<FriendListResponseDto> getFriends(String email) {

        userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return friendshipRepository.friendList(email);
    }

    @Override
    public List<FriendRequestResponseDto> getFriendRequestList(String email) {
        return friendshipRepository.friendRequestList(email);
    }

    @Override
    public Slice<FriendListResponseDto> findUserByNickname(SearchUserDto searchUserDto) {

        return friendshipRepository.findUserByNickname(searchUserDto);
    }

    @Override
    @Transactional
    public void friendRequest(String token, FriendRequestDto friendRequestDto) {

        UserEntity sender = userRepository.findByEmail(jwtProvider.getEmail(token))
                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        UserEntity receiver = userRepository.findById(friendRequestDto.getReceiverId())
                .orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        friendshipRepository.save(
                FriendshipEntity.builder()
                .sender(sender)
                .receiver(receiver)
                .status(FriendshipStatus.REQUESTING)
                .build());

        //TODO: 알람 전송
    }

    @Override
    @Transactional
    public void friendRequestAllow(FriendRequestAllowDto friendRequestAllowDto) {
        FriendshipEntity entity = friendshipRepository.findById(friendRequestAllowDto.getFriendshipId())
                .orElseThrow(()-> new CustomException(ErrorCode.REQUEST_NOT_FOUND));

        entity.updateState(friendRequestAllowDto.getFriendshipStatus());
    }
}
