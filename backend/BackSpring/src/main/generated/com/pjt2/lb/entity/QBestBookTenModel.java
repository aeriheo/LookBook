package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBestBookTenModel is a Querydsl query type for BestBookTenModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBestBookTenModel extends EntityPathBase<BestBookTenModel> {

    private static final long serialVersionUID = 626716405L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBestBookTenModel bestBookTenModel = new QBestBookTenModel("bestBookTenModel");

    public final QBook book;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QBestBookTenModel(String variable) {
        this(BestBookTenModel.class, forVariable(variable), INITS);
    }

    public QBestBookTenModel(Path<? extends BestBookTenModel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBestBookTenModel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBestBookTenModel(PathMetadata metadata, PathInits inits) {
        this(BestBookTenModel.class, metadata, inits);
    }

    public QBestBookTenModel(Class<? extends BestBookTenModel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

