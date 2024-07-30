package dev.changuii.project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_regi_date")
    private LocalDateTime registartionDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    @ManyToOne
    @JoinColumn(name = "wanted_id")
    private WantedEntity wanted;
}
