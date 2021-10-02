package com.pjt2.lb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.QUserBasedCFModel;
import com.pjt2.lb.response.BookListInfoRes;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class UserBasedCFModelRepositorySupport {

	@Autowired
	private JPAQueryFactory query;
	
	QUserBasedCFModel qUserBasedCFModel = QUserBasedCFModel.userBasedCFModel;

	public List<String> getuserBasedRecommIsbnList(String userEmail, int limitCnt) {
		List<String> userBasedRecommIsbnList = query.select(qUserBasedCFModel.book.bookIsbn).from(qUserBasedCFModel).where(qUserBasedCFModel.user.userEmail.eq(userEmail)).limit(limitCnt).fetch();
		return userBasedRecommIsbnList;
	}
	
}
