package com.pjt2.lb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.QBook;
import com.pjt2.lb.entity.QBookLike;
import com.pjt2.lb.response.LikeBookListGetRes;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class LikeRepositorySupport {
	
	@Autowired
	private JPAQueryFactory query;

	QBookLike qLike = QBookLike.like;
	QBook qBook = QBook.book;
	
	public List<LikeBookListGetRes> getLikeBookList(String userEmail) {
		// 사용자가 좋아하는 도서의 bookIsbn list
		List<String> likeBookIsbnList = query.select(qBook.bookIsbn).from(qLike).where(qLike.user.userEmail.eq(userEmail)).fetch();
		System.out.println(likeBookIsbnList);
		
		// 사용자가 좋아하는 도서 list
		// LikeBookList: bookIsbn, bookTitle, bookImgUrl 정보만 가진다.
		List<LikeBookListGetRes> LikeBookList = new ArrayList<>();
		for(String bookIsbn : likeBookIsbnList) {
			Book book = query.selectFrom(qBook).where(qBook.bookIsbn.eq(bookIsbn)).fetchOne();
			LikeBookList.add(new LikeBookListGetRes(book.getBookIsbn(), book.getBookTitle(), book.getBookImgUrl()));
		}
		return LikeBookList;
	}
}
