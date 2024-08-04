package dev.changuii.project.controller;


import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.security.service.JwtProvider;
import dev.changuii.project.service.FriendshipService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    // TODO: id 조회 -> JWT에 있는 email로 조회
    @GetMapping("")
    public ResponseEntity<List<FriendListResponseDto>> getMyFriendship(HttpServletRequest request){
//        jwtProvider.getNickname(request);
//        return ResponseEntity.ok(friendshipService.getFriends(id));
        return ResponseEntity.ok(friendshipService.getFriends(1L));
    }

    // 친구 찾기
    @GetMapping("/{nickname}")
    List<FriendListResponseDto> getMyFriendships(@PathVariable(name = "nickname")String nickname){
        return Collections.emptyList();
    }

    // 친구 요청



    // 친구 수락 여부
//    @PatchMapping("")
//    public ResponseEntity<Void> allowFriendship(@PathVariable(name = "nickname")String nickname){}


    // 친구 삭제
    @PatchMapping("/{nickname}")
    public ResponseEntity<Void> deleteFriendship(){

        return ResponseEntity.ok().build();
    }




}
