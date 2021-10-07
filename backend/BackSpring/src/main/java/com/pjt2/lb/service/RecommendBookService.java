package com.pjt2.lb.service;

import java.util.List;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.BookListInfoRes;

public interface RecommendBookService {
	List<BookListInfoRes> getItemBasedCFListInfo(int n);
	List<BookListInfoRes> getUserPredictedGradeListInfo(String userEmail, int limitCnt);
	List<BookListInfoRes> getUserBasedCFListInfo(String userEmail, int limitCnt);
	List<BookListInfoRes> getFirstBookList();
}
