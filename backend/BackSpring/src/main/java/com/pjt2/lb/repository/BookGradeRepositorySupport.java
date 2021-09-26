package com.pjt2.lb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.QBookGrade;
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
}
