package com.pjt2.lb.service;

import java.util.List;

import com.pjt2.lb.response.BookListInfoRes;

public interface RecommendBookService {

	List<BookListInfoRes> getUserBasedCFListInfo(String userEmail, int limitCnt);

}
