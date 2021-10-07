package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBasedCFModel is a Querydsl query type for UserBasedCFModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserBasedCFModel extends EntityPathBase<UserBasedCFModel> {

    private static final long serialVersionUID = -62022470L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBasedCFModel userBasedCFModel = new QUserBasedCFModel("userBasedCFModel");

    public final QBook book;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QUser user;

    public QUserBasedCFModel(String variable) {
        this(UserBasedCFModel.class, forVariable(variable), INITS);
    }

    public QUserBasedCFModel(Path<? extends UserBasedCFModel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBasedCFModel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBasedCFModel(PathMetadata metadata, PathInits inits) {
        this(UserBasedCFModel.class, metadata, inits);
    }

    public QUserBasedCFModel(Class<? extends UserBasedCFModel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

