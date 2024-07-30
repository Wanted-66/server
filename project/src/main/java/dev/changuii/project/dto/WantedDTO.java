package dev.changuii.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import dev.changuii.project.enums.WantedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class WantedDTO {

    private String title;
    private String description;
    private Integer prize;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate endDate;

    private String userEmail;


    public WantedEntity toEntity(UserEntity user, String image, WantedStatus status){
        return WantedEntity.builder()
                .title(this.title)
                .description(this.description)
                .prize(this.prize)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .status(status)
                .image(image)
                .user(user)
                .build();
    }
}
