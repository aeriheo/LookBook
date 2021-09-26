package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewLike is a Querydsl query type for ReviewLike
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReviewLike extends EntityPathBase<ReviewLike> {

    private static final long serialVersionUID = -721976341L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewLike reviewLike = new QReviewLike("reviewLike");

    public final QReview review;

    public final DateTimePath<java.util.Date> reviewLikeDate = createDateTime("reviewLikeDate", java.util.Date.class);

    public final QUser user;

    public final NumberPath<Integer> userLikeId = createNumber("userLikeId", Integer.class);

    public QReviewLike(String variable) {
        this(ReviewLike.class, forVariable(variable), INITS);
    }

    public QReviewLike(Path<? extends ReviewLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewLike(PathMetadata metadata, PathInits inits) {
        this(ReviewLike.class, metadata, inits);
    }

    public QReviewLike(Class<? extends ReviewLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

