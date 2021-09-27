package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 961780276L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final QBook book;

    public final StringPath reviewContent = createString("reviewContent");

    public final DateTimePath<java.util.Date> reviewDate = createDateTime("reviewDate", java.util.Date.class);

    public final NumberPath<Integer> reviewId = createNumber("reviewId", Integer.class);

    public final ListPath<ReviewLike, QReviewLike> reviewLike = this.<ReviewLike, QReviewLike>createList("reviewLike", ReviewLike.class, QReviewLike.class, PathInits.DIRECT2);

    public final NumberPath<Integer> reviewLikeCount = createNumber("reviewLikeCount", Integer.class);

    public final QUser user;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

