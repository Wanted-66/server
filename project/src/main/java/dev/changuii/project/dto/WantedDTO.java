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
    private String promise;
    private String category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String userEmail;


    public WantedEntity toEntity(UserEntity user, String mainImage, String signatureImage, WantedStatus status){
        return WantedEntity.builder()
                .title(this.title)
                .description(this.description)
                .prize(this.prize)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .status(status)
                .mainImage(mainImage)
                .signatureImage(signatureImage)
                .category(this.category)
                .user(user)
                .build();
    }
}
