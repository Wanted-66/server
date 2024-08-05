package dev.changuii.project.controller;


import dev.changuii.project.dto.FriendRequestAllowDto;
import dev.changuii.project.dto.FriendRequestDto;
import dev.changuii.project.dto.response.FriendshipResponseDto;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.FriendshipService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("")
    public ResponseEntity<List<FriendshipResponseDto>> getMyFriendship(HttpServletRequest request){
        String token = jwtProvider.getToken(request);
        return ResponseEntity.ok(friendshipService.getFriends(jwtProvider.getEmail(token)));
    }

    // 친구 찾기
    @GetMapping("/{nickname}")
    public ResponseEntity<List<FriendshipResponseDto>> getMyFriendships(@PathVariable(name = "nickname") String nickname,
                                                         HttpServletRequest request){
        String token = jwtProvider.getToken(request);
        return ResponseEntity.ok(friendshipService.findUserByNickname(jwtProvider.getEmail(token), nickname));
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
    @GetMapping("/request")
    public ResponseEntity<List<FriendshipResponseDto>> getFriendRequests(HttpServletRequest request){
        String token = jwtProvider.getToken(request);
        return ResponseEntity.ok(friendshipService.getFriendRequestList(jwtProvider.getEmail(token)));
    }


    // 친구 수락, 친구 삭제
    // TODO: 알람전송
    @PatchMapping("")
    public ResponseEntity<Void> allowFriendship(@RequestBody FriendRequestAllowDto friendRequestAllowDto){
        friendshipService.friendRequestAllow(friendRequestAllowDto);
        return ResponseEntity.ok().build();
    }



}
