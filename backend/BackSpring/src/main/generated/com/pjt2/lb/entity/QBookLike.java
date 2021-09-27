package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookLike is a Querydsl query type for BookLike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookLike extends EntityPathBase<BookLike> {

    private static final long serialVersionUID = -1176494596L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookLike bookLike = new QBookLike("bookLike");

    public final QBook book;

    public final DateTimePath<java.util.Date> bookLikeDate = createDateTime("bookLikeDate", java.util.Date.class);

    public final NumberPath<Integer> bookLikeId = createNumber("bookLikeId", Integer.class);

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

