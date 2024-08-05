package dev.changuii.project.controller;


import dev.changuii.project.dto.FriendRequestAllowDto;
import dev.changuii.project.dto.FriendRequestDto;
import dev.changuii.project.dto.SearchUserDto;
import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.dto.response.FriendRequestResponseDto;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.FriendshipService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;
    private final JwtProvider jwtProvider;

    public FriendshipController(@Autowired FriendshipService friendshipService, JwtProvider jwtProvider) {
        this.friendshipService = friendshipService;
        this.jwtProvider = jwtProvider;
    }

    // 내 친구목록 조회

    // 엔티티 고치가 -> friendship id 포함 하도록
    @GetMapping("")
    public ResponseEntity<List<FriendListResponseDto>> getMyFriendship(HttpServletRequest request){
        String token = jwtProvider.getToken(request);
        return ResponseEntity.ok(friendshipService.getFriends(jwtProvider.getEmail(token)));
    }

    // 친구 찾기
    @GetMapping("/search")
    public Slice<FriendListResponseDto> getMyFriendships(@RequestBody SearchUserDto searchUserDto){
        return friendshipService.findUserByNickname(searchUserDto);
    }

    // 친구 요청 보내기
    @PostMapping("")
    public ResponseEntity<Void> friendRequest(@RequestBody FriendRequestDto friendRequestDto, HttpServletRequest request)
    {
        String token = jwtProvider.getToken(request);
        friendshipService.friendRequest(token, friendRequestDto);
        return ResponseEntity.ok().build();
    }

    // 내게 온 친구 요청 조회
    @GetMapping("/requset")
    public ResponseEntity<List<FriendRequestResponseDto>> getFriendRequests(HttpServletRequest request){
        String token = jwtProvider.getToken(request);
//        friendshipService.getFriendRequestList(jwtProvider.getEmail(token));
        return ResponseEntity.ok(friendshipService.getFriendRequestList(jwtProvider.getEmail(token)));
    }


    // 친구 수락 여부결정
    // TODO: 알람전송?
    @PatchMapping("")
    public ResponseEntity<Void> allowFriendship(@RequestBody FriendRequestAllowDto friendRequestAllowDto){
        return ResponseEntity.ok().build();
    }


    // 친구 삭제
    @PatchMapping("/{nickname}")
    public ResponseEntity<Void> deleteFriendship(){

        return ResponseEntity.ok().build();
    }




}
