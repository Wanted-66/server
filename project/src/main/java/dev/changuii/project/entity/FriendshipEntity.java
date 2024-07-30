package dev.changuii.project.entity;

import dev.changuii.project.enums.FriendshipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class FriendshipEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저는 친구 요청을 보내는 사람으로써 user와 friendship간에 1:N의 관계를 갖는다.
    // 즉, 유저는 여러 친구 요청을 보낼 수 있고 friendship은 각각의 친구요청에 대해 하나의 요청을 보내는 유저를 갖는다.
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    // 유저는 친구 요청을 받는 사람으로써 user와 friendship 간에 1 : N의 관계를 갖는다.
    // 즉, 유저는 여러 친구 요청을 받을 수 있고, friendship은 각각의 친구 요청에 대해 하나의 요청을 받는 유저를 갖는다.
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    @Column(name = "friendship_status")
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

}
