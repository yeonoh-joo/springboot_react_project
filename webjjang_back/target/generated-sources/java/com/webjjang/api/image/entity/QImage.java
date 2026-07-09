package com.webjjang.api.image.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = -1201221070L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImage image = new QImage("image");

    public final StringPath content = createString("content");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> hit = createNumber("hit", Long.class);

    protected com.webjjang.api.member.entity.QMember member;

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> writedDate = createDateTime("writedDate", java.time.LocalDateTime.class);

    public QImage(String variable) {
        this(Image.class, forVariable(variable), INITS);
    }

    public QImage(Path<? extends Image> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImage(PathMetadata metadata, PathInits inits) {
        this(Image.class, metadata, inits);
    }

    public QImage(Class<? extends Image> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.webjjang.api.member.entity.QMember(forProperty("member")) : null;
    }

    public com.webjjang.api.member.entity.QMember member() {
        if (member == null) {
            member = new com.webjjang.api.member.entity.QMember(forProperty("member"));
        }
        return member;
    }

}

