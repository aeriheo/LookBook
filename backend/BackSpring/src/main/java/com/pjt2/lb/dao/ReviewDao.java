package com.pjt2.lb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pjt2.lb.response.BookReviewListInfoRes;

import com.pjt2.lb.response.UserReviewListInfoRes;

@Mapper
public interface ReviewDao {
	public List<BookReviewListInfoRes> getBookRecentReviewList(@Param("bookIsbn") String bookIsbn, @Param("userEmail") String userEmail);
	public List<UserReviewListInfoRes> getUserReviewList(String userEmail);
	public List<BookReviewListInfoRes> getBookRecommReviewList(@Param("bookIsbn") String bookIsbn, @Param("userEmail") String userEmail);
}
