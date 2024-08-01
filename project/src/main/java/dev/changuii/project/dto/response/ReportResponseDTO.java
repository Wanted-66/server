package dev.changuii.project.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import dev.changuii.project.dto.ReportDTO;
import dev.changuii.project.enums.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ReportResponseDTO {

    private Long id;
    private String image;
    private String description;
    private ReportStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;

    private String username;


}
