package com.pjt2.lb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLibrary is a Querydsl query type for Library
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLibrary extends EntityPathBase<Library> {

    private static final long serialVersionUID = -1183326881L;

    public static final QLibrary library = new QLibrary("library");

    public final ListPath<Book, QBook> book = this.<Book, QBook>createList("book", Book.class, QBook.class, PathInits.DIRECT2);

    public final StringPath libAddr = createString("libAddr");

    public final StringPath libCode = createString("libCode");

    public final StringPath libGugun = createString("libGugun");

    public final StringPath libLang = createString("libLang");

    public final StringPath libLat = createString("libLat");

    public final StringPath libName = createString("libName");

    public final StringPath libSido = createString("libSido");

    public final StringPath libTel = createString("libTel");

    public final StringPath libUrl = createString("libUrl");

    public QLibrary(String variable) {
        super(Library.class, forVariable(variable));
    }

    public QLibrary(Path<? extends Library> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLibrary(PathMetadata metadata) {
        super(Library.class, metadata);
    }

}

