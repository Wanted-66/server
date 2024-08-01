package dev.changuii.project.entity;


import dev.changuii.project.dto.response.WantedResponseDTO;
import dev.changuii.project.enums.WantedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Enumerated(EnumType.STRING)
    private WantedStatus status;

    @Column(name = "wanted_main_image")
    private String mainImage;

    @Column(name = "wanted_signature_image")
    private String signatureImage;

    @Column(name = "wanted_promise")
    private String promise;

    @Column(name = "wanted_category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "wanted")
    private List<ReportEntity> reports = new ArrayList<>();

    @OneToMany(mappedBy = "wanted")
    private List<CommentEntity> comments = new ArrayList<>();

    public WantedResponseDTO toResponseDTO(){
        return WantedResponseDTO.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .prize(this.prize)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .mainImage(this.mainImage)

                .signatureImage(this.signatureImage)
                .promise(this.promise)
                .category(this.category)
                .status(this.status)
                .build();
    }

    public static List<WantedResponseDTO> toResponseDTOList(List<WantedEntity> wantedEntityList){
        return wantedEntityList.stream()
                .map(WantedEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    public WantedEntity modifyStatus(String status){
        this.status = WantedStatus.valueOf(status);
        return this;
    }

}
