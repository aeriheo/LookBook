package com.pjt2.lb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.repository.BookRepositorySupport;
import com.pjt2.lb.repository.UserBasedCFModelRepositorySupport;
import com.pjt2.lb.response.BookListInfoRes;

@Service("RecommendBookService")
public class RecommendBookServiceImpl implements RecommendBookService{
	
	@Autowired
	UserBasedCFModelRepositorySupport userBasedCFModelRepositorySupport;
	
	@Autowired
	BookRepositorySupport bookRepositorySupport;

	@Override
	public List<BookListInfoRes> getUserBasedCFListInfo(String userEmail, int limitCnt) {
		List<String> userBasedRecommIsbnList = userBasedCFModelRepositorySupport.getUserBasedRecommIsbnList(userEmail, limitCnt);
		List<BookListInfoRes> userBasedRecommList = new ArrayList<>();
		for(String isbn : userBasedRecommIsbnList) {
			userBasedRecommList.add(bookRepositorySupport.getListBookInfo(isbn));
		}
		return userBasedRecommList;
	}

}
