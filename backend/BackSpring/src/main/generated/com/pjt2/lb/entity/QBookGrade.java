package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookGrade is a Querydsl query type for BookGrade
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookGrade extends EntityPathBase<BookGrade> {

    private static final long serialVersionUID = -2115953134L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookGrade bookGrade1 = new QBookGrade("bookGrade1");

    public final QBook book;

    public final NumberPath<Integer> bookGrade = createNumber("bookGrade", Integer.class);

    public final NumberPath<Integer> bookGradeId = createNumber("bookGradeId", Integer.class);

    public final QUser user;

    public QBookGrade(String variable) {
        this(BookGrade.class, forVariable(variable), INITS);
    }

    public QBookGrade(Path<? extends BookGrade> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookGrade(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookGrade(PathMetadata metadata, PathInits inits) {
        this(BookGrade.class, metadata, inits);
    }

    public QBookGrade(Class<? extends BookGrade> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

