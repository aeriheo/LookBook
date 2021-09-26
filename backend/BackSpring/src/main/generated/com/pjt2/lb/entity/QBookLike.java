package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLike is a Querydsl query type for Like
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookLike extends EntityPathBase<BookLike> {

    private static final long serialVersionUID = 1685739891L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookLike like = new QBookLike("like1");

    public final QBook book;

    public final DateTimePath<java.util.Date> likeDate = createDateTime("likeDate", java.util.Date.class);

    public final NumberPath<Integer> likeId = createNumber("likeId", Integer.class);

    public final QUser user;

    public QBookLike(String variable) {
        this(BookLike.class, forVariable(variable), INITS);
    }

    public QBookLike(Path<? extends BookLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookLike(PathMetadata metadata, PathInits inits) {
        this(BookLike.class, metadata, inits);
    }

    public QBookLike(Class<? extends BookLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }
    
}

