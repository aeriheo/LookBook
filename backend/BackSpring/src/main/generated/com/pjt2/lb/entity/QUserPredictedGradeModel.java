package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPredictedGradeModel is a Querydsl query type for UserPredictedGradeModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserPredictedGradeModel extends EntityPathBase<UserPredictedGradeModel> {

    private static final long serialVersionUID = -1597986365L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPredictedGradeModel userPredictedGradeModel = new QUserPredictedGradeModel("userPredictedGradeModel");

    public final QBook book;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QUser user;

    public QUserPredictedGradeModel(String variable) {
        this(UserPredictedGradeModel.class, forVariable(variable), INITS);
    }

    public QUserPredictedGradeModel(Path<? extends UserPredictedGradeModel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPredictedGradeModel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPredictedGradeModel(PathMetadata metadata, PathInits inits) {
        this(UserPredictedGradeModel.class, metadata, inits);
    }

    public QUserPredictedGradeModel(Class<? extends UserPredictedGradeModel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

