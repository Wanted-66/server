package dev.changuii.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFriendshipEntity is a Querydsl query type for FriendshipEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFriendshipEntity extends EntityPathBase<FriendshipEntity> {

    private static final long serialVersionUID = -235091458L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFriendshipEntity friendshipEntity = new QFriendshipEntity("friendshipEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity receiver;

    public final QUserEntity sender;

    public final EnumPath<dev.changuii.project.enums.FriendshipStatus> status = createEnum("status", dev.changuii.project.enums.FriendshipStatus.class);

    public QFriendshipEntity(String variable) {
        this(FriendshipEntity.class, forVariable(variable), INITS);
    }

    public QFriendshipEntity(Path<? extends FriendshipEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFriendshipEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFriendshipEntity(PathMetadata metadata, PathInits inits) {
        this(FriendshipEntity.class, metadata, inits);
    }

    public QFriendshipEntity(Class<? extends FriendshipEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.receiver = inits.isInitialized("receiver") ? new QUserEntity(forProperty("receiver")) : null;
        this.sender = inits.isInitialized("sender") ? new QUserEntity(forProperty("sender")) : null;
    }

}

