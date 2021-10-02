package com.pjt2.lb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookRepositorySupport;
import com.pjt2.lb.repository.ItemBasedCFModelRepositorySupport;
import com.pjt2.lb.repository.UserPredictedGradeModelRepositorySupport;
import com.pjt2.lb.response.BookListInfoRes;

@Service("RecommendBookService")
public class RecommendBookServiceImpl implements RecommendBookService{

	@Autowired
	ItemBasedCFModelRepositorySupport itemBasedCFModelRepositorySupport;
	
	@Autowired
	UserPredictedGradeModelRepositorySupport userPredictedGradeModelRepositorySupport;
	
	@Autowired
	BookRepositorySupport bookRepositorySupport;
	
	@Override
	public List<BookListInfoRes> getItemBasedCFListInfo(int n) {
		List<String> itemBasedRecommIsbnList = itemBasedCFModelRepositorySupport.getItemBasedRecommIsbnList(n);
		List<BookListInfoRes> itemBasedCFList = new ArrayList<>();
		for(String itemBasedRecommIsbn : itemBasedRecommIsbnList)
			itemBasedCFList.add(bookRepositorySupport.getListBookInfo(itemBasedRecommIsbn));
		return itemBasedCFList;
	}

	@Override
	public List<BookListInfoRes> getUserPredictedGradeListInfo(User user, int n) {
		List<String> userPredictedRecommIsbnList = userPredictedGradeModelRepositorySupport.getUserPredictedRecommIsbnList(user.getUserEmail(), n);
		List<BookListInfoRes> userPredictedGradeList = new ArrayList<>();;
		for(String userPredictedRecommIsbn : userPredictedRecommIsbnList)
			userPredictedGradeList.add(bookRepositorySupport.getListBookInfo(userPredictedRecommIsbn));
		return userPredictedGradeList;
	}

	
}
