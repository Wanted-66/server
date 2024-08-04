package dev.changuii.project.controller;


import dev.changuii.project.dto.response.FriendListResponseDto;
import dev.changuii.project.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(@Autowired FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    // 내 친구목록 조회
    // TODO: id 조회 -> JWT에 있는 email로 조회
    @GetMapping("/{id}")
    public ResponseEntity<List<FriendListResponseDto>> getMyFriendship(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(friendshipService.getFriends(id));
    }



    // 친구 요청

    // 친구 수락

    // 친구 삭제


}
