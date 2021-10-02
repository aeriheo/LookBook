package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemBasedCFModel is a Querydsl query type for ItemBasedCFModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemBasedCFModel extends EntityPathBase<ItemBasedCFModel> {

    private static final long serialVersionUID = 449019906L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemBasedCFModel itemBasedCFModel = new QItemBasedCFModel("itemBasedCFModel");

    public final QBook book;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QItemBasedCFModel(String variable) {
        this(ItemBasedCFModel.class, forVariable(variable), INITS);
    }

    public QItemBasedCFModel(Path<? extends ItemBasedCFModel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemBasedCFModel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemBasedCFModel(PathMetadata metadata, PathInits inits) {
        this(ItemBasedCFModel.class, metadata, inits);
    }

    public QItemBasedCFModel(Class<? extends ItemBasedCFModel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

