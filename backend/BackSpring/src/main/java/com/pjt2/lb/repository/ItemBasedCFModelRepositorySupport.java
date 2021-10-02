package com.pjt2.lb.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.QItemBasedCFModel;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ItemBasedCFModelRepositorySupport{
	@Autowired
	private JPAQueryFactory query;
	
	QItemBasedCFModel qItemBasedCFModel = QItemBasedCFModel.itemBasedCFModel;

	public List<String> getItemBasedRecommIsbnList(int limitCnt){
		return query.select(qItemBasedCFModel.book.bookIsbn).from(qItemBasedCFModel).limit(limitCnt).fetch();
	}
}
