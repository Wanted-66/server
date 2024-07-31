package dev.changuii.project.service;


import dev.changuii.project.dto.CommentDTO;
import dev.changuii.project.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    public CommentResponseDTO createComment(CommentDTO commentDTO, Long wantedId);
    public List<CommentResponseDTO> readAllByWantedId(Long wantedId);

}
