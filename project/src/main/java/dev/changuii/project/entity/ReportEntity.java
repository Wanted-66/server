package dev.changuii.project.entity;


import dev.changuii.project.dto.response.ReportResponseDTO;
import dev.changuii.project.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ReportEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_image")
    private String image;

    @Column(name = "report_description")
    private String description;

    @Column(name = "report_status")
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column(name = "report_regi_date")
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "wanted_id")
    private WantedEntity wanted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;



    public ReportResponseDTO toResponseDTO(){
        return ReportResponseDTO.builder()
                .id(this.id)
                .image(this.image)
                .description(this.description)
                .status(this.status)
                .registrationDate(this.registrationDate)
                .username(this.user.getName()).build();
    }

    public static List<ReportResponseDTO> toResponseDTOList(List<ReportEntity> reportEntityList){
        return reportEntityList.stream()
                .map(ReportEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

}
