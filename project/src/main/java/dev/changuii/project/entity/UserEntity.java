package dev.changuii.project.entity;


import dev.changuii.project.enums.UserDesignation;
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
@AllArgsConstructor @NoArgsConstructor
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_p_num")
    private String phoneNum;

    @Column(name = "user_email")
    private String email;

    @Column(name =  "user_p_image")
    private String profileImage;

    @Column(name=  "user_regi_date")
    private LocalDate registerDate;

    @Column(name = "user_intro")
    private String introduction;

    @Column(name = "user_nickname")
    private String nickname;

    @Column(name = "user_bank_account")
    private String bankAccount;

    @Column(name = "user_designation")
    private UserDesignation designation;

    @OneToMany(mappedBy = "writer")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<WantedEntity> wantedList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReportEntity> reports = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<FriendshipEntity> sendFriendship = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<FriendshipEntity> receiverFriendship = new ArrayList<>();

}
