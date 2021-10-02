package com.pjt2.lb.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.ItemBasedCFModel;
import com.pjt2.lb.entity.QItemBasedCFModel;
import com.pjt2.lb.entity.QUserPredictedGradeModel;
import com.pjt2.lb.entity.UserPredictedGradeModel;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserPredictedGradeModelRepositorySupport {
	@Autowired
	private JPAQueryFactory query;
	
	QUserPredictedGradeModel qUserPredictedGradeModel = QUserPredictedGradeModel.userPredictedGradeModel;

	public List<String> getUserPredictedRecommIsbnList(String userEmail, int limitCnt){
		return query.select(qUserPredictedGradeModel.book.bookIsbn).from(qUserPredictedGradeModel).where(qUserPredictedGradeModel.user.userEmail.eq(userEmail)).limit(limitCnt).fetch();
	}
}
