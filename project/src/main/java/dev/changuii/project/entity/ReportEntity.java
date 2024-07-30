package dev.changuii.project.entity;


import dev.changuii.project.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private ReportStatus status;

    @Column(name = "report_regi_date")
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "wanted_id")
    private WantedEntity wanted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
