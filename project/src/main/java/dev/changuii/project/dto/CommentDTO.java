package dev.changuii.project.dto;


import dev.changuii.project.entity.CommentEntity;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentDTO {

    @Size(min = 1, max = 25, message = "최소 1자 이상 최대 25자")
    private String content;
    private String writerEmail;


    public CommentEntity toEntity(UserEntity writer, WantedEntity wanted){
        return CommentEntity.builder()
                .content(this.content)
                .writeDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .writer(writer)
                .wanted(wanted)
                .build();
    }
}
