package dev.changuii.project.entity;


import dev.changuii.project.enums.WantedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class WantedEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wanted_id")
    private Long id;

    @Column(name = "wanted_title")
    private String title;

    @Column(name =  "wanted_description")
    private String description;

    @Column(name = "wanted_prize")
    private Integer prize;

    @Column(name = "wanted_start_date")
    private LocalDate startDate;

    @Column(name = "wanted_end_date")
    private LocalDate endDate;

    @Column(name="wanted_status")
    private WantedStatus status;

    @Column(name = "wanted_image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "wanted")
    private List<ReportEntity> reports = new ArrayList<>();

    @OneToMany(mappedBy = "wanted")
    private List<CommentEntity> comments = new ArrayList<>();


}
