package dev.changuii.project.controller;


import dev.changuii.project.dto.CommentDTO;
import dev.changuii.project.dto.response.CommentResponseDTO;
import dev.changuii.project.service.CommentService;
import dev.changuii.project.service.IdempotentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/wanted/{wantedId}/comment")
public class CommentController {

    private final CommentService commentService;
    private final IdempotentService idempotentService;
    private final String NAME = "COMMENT";


    public CommentController(
            @Autowired CommentService commentService,
            @Autowired IdempotentService idempotentService
    ){
        this.idempotentService=idempotentService;
        this.commentService = commentService;
    }

    // todo : 이메일 가져오는 로직 변경해야함
    @PostMapping()
    public ResponseEntity<CommentResponseDTO> createComment(
            @RequestBody CommentDTO commentDTO,
            @PathVariable("wantedId") Long wantedId
            ){
        this.idempotentService.isValidIdempotent(Arrays.asList(new String[]{NAME, "POST", commentDTO.getWriterEmail()}));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.commentService.createComment(commentDTO, wantedId));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> readAllByWantedId(
            @PathVariable("wantedId") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.commentService.readAllByWantedId(id));
    }




}
