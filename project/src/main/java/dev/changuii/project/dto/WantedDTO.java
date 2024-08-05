package dev.changuii.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import dev.changuii.project.enums.WantedStatus;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class WantedDTO {

    @Size(min = 1, max = 10, message = "최소 1자 이상 최대 10자 이하")
    private String title;
    @Size(min = 1, max = 100, message = "최소 1자 이상 최대 100자 이하")
    private String description;
    @PositiveOrZero
    private Integer prize;
    @Size(min = 1, max = 50, message = "최소 1자 이상 최대 50자 이하")
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
                .promise(this.promise)
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
