package dev.changuii.project.controller;


import dev.changuii.project.dto.CommentDTO;
import dev.changuii.project.dto.response.CommentResponseDTO;
import dev.changuii.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wanted/{wantedId}/comment")
public class CommentController {

    private final CommentService commentService;


    public CommentController(
            @Autowired CommentService commentService
    ){
        this.commentService = commentService;
    }

    @PostMapping()
    public ResponseEntity<CommentResponseDTO> createComment(
            @RequestBody CommentDTO commentDTO,
            @PathVariable("wantedId") Long wantedId
            ){
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
