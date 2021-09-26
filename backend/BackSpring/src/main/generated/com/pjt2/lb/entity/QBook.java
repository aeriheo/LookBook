package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = 1685447877L;

    public static final QBook book = new QBook("book");

    public final StringPath bookAuthor = createString("bookAuthor");

    public final StringPath bookCategoryCode = createString("bookCategoryCode");

    public final StringPath bookDesc = createString("bookDesc");

    public final ListPath<BookGrade, QBookGrade> bookGrade = this.<BookGrade, QBookGrade>createList("bookGrade", BookGrade.class, QBookGrade.class, PathInits.DIRECT2);

    public final StringPath bookImgUrl = createString("bookImgUrl");

    public final StringPath bookIsbn = createString("bookIsbn");

    public final StringPath bookKeyword1 = createString("bookKeyword1");

    public final StringPath bookKeyword2 = createString("bookKeyword2");

    public final StringPath bookKeyword3 = createString("bookKeyword3");

    public final NumberPath<Integer> bookPrice = createNumber("bookPrice", Integer.class);

    public final StringPath bookPub = createString("bookPub");

    public final StringPath bookPubDate = createString("bookPubDate");

    public final StringPath bookTitle = createString("bookTitle");

    public final ListPath<Library, QLibrary> library = this.<Library, QLibrary>createList("library", Library.class, QLibrary.class, PathInits.DIRECT2);

    public final ListPath<BookLike, QBookLike> like = this.<BookLike, QBookLike>createList("like", BookLike.class, QBookLike.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> review = this.<Review, QReview>createList("review", Review.class, QReview.class, PathInits.DIRECT2);

    public final ListPath<SearchLog, QSearchLog> searchLog = this.<SearchLog, QSearchLog>createList("searchLog", SearchLog.class, QSearchLog.class, PathInits.DIRECT2);

    public QBook(String variable) {
        super(Book.class, forVariable(variable));
    }

    public QBook(Path<? extends Book> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBook(PathMetadata metadata) {
        super(Book.class, metadata);
    }

}

