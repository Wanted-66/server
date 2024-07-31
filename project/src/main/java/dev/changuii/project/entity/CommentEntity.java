package dev.changuii.project.entity;


import dev.changuii.project.dto.response.CommentResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_write_date")
    private LocalDateTime writeDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    @ManyToOne
    @JoinColumn(name = "wanted_id")
    private WantedEntity wanted;

    public CommentResponseDTO toResponseDTO(){
        return CommentResponseDTO.builder()
                .id(this.id)
                .content(this.content)
                .writeDate(this.writeDate)
                .username(this.writer.getName())
                .build();
    }

    public static List<CommentResponseDTO> toResponseDTOList(List<CommentEntity> commentEntityList){
        return commentEntityList.stream()
                .map((e) -> e.toResponseDTO()).collect(Collectors.toList());
    }
}
