package dev.changuii.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1055187951L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final StringPath bankAccount = createString("bankAccount");

    public final ListPath<CommentEntity, QCommentEntity> comments = this.<CommentEntity, QCommentEntity>createList("comments", CommentEntity.class, QCommentEntity.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath phoneNum = createString("phoneNum");

    public final StringPath profileImage = createString("profileImage");

    public final ListPath<FriendshipEntity, QFriendshipEntity> receiverFriendship = this.<FriendshipEntity, QFriendshipEntity>createList("receiverFriendship", FriendshipEntity.class, QFriendshipEntity.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> registerDate = createDate("registerDate", java.time.LocalDate.class);

    public final ListPath<ReportEntity, QReportEntity> reports = this.<ReportEntity, QReportEntity>createList("reports", ReportEntity.class, QReportEntity.class, PathInits.DIRECT2);

    public final ListPath<FriendshipEntity, QFriendshipEntity> sendFriendship = this.<FriendshipEntity, QFriendshipEntity>createList("sendFriendship", FriendshipEntity.class, QFriendshipEntity.class, PathInits.DIRECT2);

    public final ListPath<dev.changuii.project.enums.UserDesignation, EnumPath<dev.changuii.project.enums.UserDesignation>> userDesignationList = this.<dev.changuii.project.enums.UserDesignation, EnumPath<dev.changuii.project.enums.UserDesignation>>createList("userDesignationList", dev.changuii.project.enums.UserDesignation.class, EnumPath.class, PathInits.DIRECT2);

    public final ListPath<WantedEntity, QWantedEntity> wantedList = this.<WantedEntity, QWantedEntity>createList("wantedList", WantedEntity.class, QWantedEntity.class, PathInits.DIRECT2);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

