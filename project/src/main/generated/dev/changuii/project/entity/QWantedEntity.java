package dev.changuii.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWantedEntity is a Querydsl query type for WantedEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWantedEntity extends EntityPathBase<WantedEntity> {

    private static final long serialVersionUID = 182584211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWantedEntity wantedEntity = new QWantedEntity("wantedEntity");

    public final StringPath category = createString("category");

    public final ListPath<CommentEntity, QCommentEntity> comments = this.<CommentEntity, QCommentEntity>createList("comments", CommentEntity.class, QCommentEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mainImage = createString("mainImage");

    public final NumberPath<Integer> prize = createNumber("prize", Integer.class);

    public final StringPath promise = createString("promise");

    public final ListPath<ReportEntity, QReportEntity> reports = this.<ReportEntity, QReportEntity>createList("reports", ReportEntity.class, QReportEntity.class, PathInits.DIRECT2);

    public final StringPath signatureImage = createString("signatureImage");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<dev.changuii.project.enums.WantedStatus> status = createEnum("status", dev.changuii.project.enums.WantedStatus.class);

    public final StringPath title = createString("title");

    public final QUserEntity user;

    public QWantedEntity(String variable) {
        this(WantedEntity.class, forVariable(variable), INITS);
    }

    public QWantedEntity(Path<? extends WantedEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWantedEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWantedEntity(PathMetadata metadata, PathInits inits) {
        this(WantedEntity.class, metadata, inits);
    }

    public QWantedEntity(Class<? extends WantedEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user")) : null;
    }

}

