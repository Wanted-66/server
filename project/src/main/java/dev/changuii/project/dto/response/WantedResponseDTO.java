package dev.changuii.project.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import dev.changuii.project.enums.WantedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor @AllArgsConstructor
public class WantedResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Integer prize;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private WantedStatus status;
    private String mainImage;
    private String signatureImage;
    private String promise;
    private String category;

}