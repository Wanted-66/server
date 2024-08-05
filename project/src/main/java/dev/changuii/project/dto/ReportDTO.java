package dev.changuii.project.dto;

import dev.changuii.project.entity.ReportEntity;
import dev.changuii.project.entity.UserEntity;
import dev.changuii.project.entity.WantedEntity;
import dev.changuii.project.enums.ReportStatus;
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
public class ReportDTO {

    @Size(min = 1, max = 500, message = "최소 1자 이상 최대 500자 이하")
    private String description;
    private ReportStatus status;
    private String userEmail;



    public ReportEntity toEntity(String image, WantedEntity wanted, UserEntity user){
        return ReportEntity.builder()
                .image(image)
                .description(this.description)
                .status(ReportStatus.VOTING)
                .registrationDate(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .wanted(wanted)
                .user(user).build();
    }



}
