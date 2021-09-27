package com.pjt2.lb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.QBook;
import com.pjt2.lb.entity.QBookLike;
import com.pjt2.lb.response.BookListInfoRes;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BookLikeRepositorySupport {
	
	@Autowired
	private JPAQueryFactory query;

	QBookLike qBookLike = QBookLike.bookLike;
	QBook qBook = QBook.book;
	
	public List<BookListInfoRes> getLikeBookList(String userEmail) {
		List<String> likeBookIsbnList = query.select(qBook.bookIsbn).from(qBookLike).where(qBookLike.user.userEmail.eq(userEmail)).fetch();
		System.out.println(likeBookIsbnList);
		
		List<BookListInfoRes> LikeBookList = new ArrayList<>();
		for(String bookIsbn : likeBookIsbnList) {
			Book book = query.selectFrom(qBook).where(qBook.bookIsbn.eq(bookIsbn)).fetchOne();
			LikeBookList.add(new BookListInfoRes(book.getBookIsbn(), book.getBookTitle(), book.getBookImgUrl()));
		}
		return LikeBookList;
	}
}
