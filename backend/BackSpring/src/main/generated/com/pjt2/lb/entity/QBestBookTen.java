package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBestBookTen is a Querydsl query type for BestBookTen
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBestBookTen extends EntityPathBase<BestBookTen> {

    private static final long serialVersionUID = -296068972L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBestBookTen bestBookTen = new QBestBookTen("bestBookTen");

    public final QBook book;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QBestBookTen(String variable) {
        this(BestBookTen.class, forVariable(variable), INITS);
    }

    public QBestBookTen(Path<? extends BestBookTen> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBestBookTen(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBestBookTen(PathMetadata metadata, PathInits inits) {
        this(BestBookTen.class, metadata, inits);
    }

    public QBestBookTen(Class<? extends BestBookTen> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

