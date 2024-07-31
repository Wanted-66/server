package dev.changuii.project.service.impl;

import dev.changuii.project.dto.CommentDTO;
import dev.changuii.project.dto.response.CommentResponseDTO;
import dev.changuii.project.entity.CommentEntity;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import dev.changuii.project.enums.ErrorCode;
import dev.changuii.project.exception.CustomException;
import dev.changuii.project.repository.CommentRepository;
import dev.changuii.project.repository.UserRepository;
import dev.changuii.project.repository.WantedRepository;
import dev.changuii.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final WantedRepository wantedRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(
            @Autowired WantedRepository wantedRepository,
            @Autowired CommentRepository commentRepository,
            @Autowired UserRepository userRepository
    ){
        this.wantedRepository=wantedRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }



    @Override
    public CommentResponseDTO createComment(CommentDTO commentDTO, Long wantedId) {
        WantedEntity wanted = this.wantedRepository.findById(wantedId)
                .orElseThrow(() ->  new CustomException(ErrorCode.WANTED_NOT_FOUND));

        UserEntity writer = this.userRepository.findByEmail(commentDTO.getWriterEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        CommentEntity commentEntity = commentDTO.toEntity(writer, wanted);
        commentEntity = this.commentRepository.save(commentEntity);

        return commentEntity.toResponseDTO();
    }

    @Override
    public List<CommentResponseDTO> readAllByWantedId(Long wantedId) {
        WantedEntity wanted = this.wantedRepository.findById(wantedId)
                .orElseThrow(() ->  new CustomException(ErrorCode.WANTED_NOT_FOUND));

        List<CommentEntity> commentEntityList = this.commentRepository.readAllByWanted(wanted);


        return CommentEntity.toResponseDTOList(commentEntityList);
    }
}
