package com.pjt2.lb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.QBookGrade;
import com.pjt2.lb.response.BookGradeListInfoRes;
import com.pjt2.lb.response.BookListInfoRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BookGradeRepositorySupport {

	@Autowired
	private JPAQueryFactory query;
	
	QBookGrade qBookGrade = QBookGrade.bookGrade1;
	
	public int getBookGrade(String bookIsbn, String userEmail) {
		int bookGrade = query.select(qBookGrade.bookGrade).from(qBookGrade).
				where(qBookGrade.book.bookIsbn.eq(bookIsbn), qBookGrade.user.userEmail.eq(userEmail)).fetchOne();
		return bookGrade;
	}
	
	public double getBookGradeAvg(String bookIsbn) {
		Double bookGradeAvg = query.select(qBookGrade.bookGrade.avg()).from(qBookGrade).
				where(qBookGrade.book.bookIsbn.eq(bookIsbn)).fetchOne();
		if(bookGradeAvg == null) return 0;
		return bookGradeAvg;
	}
	
	public List<BookGradeListInfoRes> getBookGradeList(String userEmail) {
		List<BookGradeListInfoRes> bookGradeListInfoRes = query.select(Projections.bean(BookGradeListInfoRes.class, qBookGrade.book.bookIsbn, qBookGrade.book.bookTitle, qBookGrade.book.bookImgUrl, qBookGrade.bookGrade))
				.from(qBookGrade).where(qBookGrade.user.userEmail.eq(userEmail))
				.fetch();
		return bookGradeListInfoRes;
		
	}
}
