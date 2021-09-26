package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1686017447L;

    public static final QUser user = new QUser("user");

    public final ListPath<BookGrade, QBookGrade> bookGrade = this.<BookGrade, QBookGrade>createList("bookGrade", BookGrade.class, QBookGrade.class, PathInits.DIRECT2);

    public final ListPath<Like, QLike> like = this.<Like, QLike>createList("like", Like.class, QLike.class, PathInits.DIRECT2);

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<Review, QReview> review = this.<Review, QReview>createList("review", Review.class, QReview.class, PathInits.DIRECT2);

    public final ListPath<ReviewLike, QReviewLike> reviewLike = this.<ReviewLike, QReviewLike>createList("reviewLike", ReviewLike.class, QReviewLike.class, PathInits.DIRECT2);

    public final ListPath<SearchLog, QSearchLog> searchLog = this.<SearchLog, QSearchLog>createList("searchLog", SearchLog.class, QSearchLog.class, PathInits.DIRECT2);

    public final StringPath userEmail = createString("userEmail");

    public final NumberPath<Integer> userJoinType = createNumber("userJoinType", Integer.class);

    public final StringPath userName = createString("userName");

    public final StringPath userNickname = createString("userNickname");

    public final StringPath userPassword = createString("userPassword");

    public final StringPath userProfileUrl = createString("userProfileUrl");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

