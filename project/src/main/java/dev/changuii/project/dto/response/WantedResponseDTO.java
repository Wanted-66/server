package dev.changuii.project.dto.response;


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
    private LocalDate startDate;
    private LocalDate endDate;
    private WantedStatus status;
    private String image;

}