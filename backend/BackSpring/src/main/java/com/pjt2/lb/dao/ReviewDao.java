package com.pjt2.lb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pjt2.lb.response.BookReviewListInfoRes;

@Mapper
public interface ReviewDao {

	public List<BookReviewListInfoRes> getBookReviewList(@Param("bookIsbn") String bookIsbn, @Param("userEmail") String userEmail);

}
