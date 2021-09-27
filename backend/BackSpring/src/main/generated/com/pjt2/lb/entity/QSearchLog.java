package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSearchLog is a Querydsl query type for SearchLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSearchLog extends EntityPathBase<SearchLog> {

    private static final long serialVersionUID = 1954620448L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSearchLog searchLog = new QSearchLog("searchLog");

    public final QBook book;

    public final DateTimePath<java.util.Date> logDate = createDateTime("logDate", java.util.Date.class);

    public final NumberPath<Integer> logId = createNumber("logId", Integer.class);

    public final QUser user;

    public QSearchLog(String variable) {
        this(SearchLog.class, forVariable(variable), INITS);
    }

    public QSearchLog(Path<? extends SearchLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSearchLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSearchLog(PathMetadata metadata, PathInits inits) {
        this(SearchLog.class, metadata, inits);
    }

    public QSearchLog(Class<? extends SearchLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

