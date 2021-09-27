package com.pjt2.lb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt2.lb.response.UserReviewListInfoRes;

@Mapper
public interface ReviewDao {
	public List<UserReviewListInfoRes> getUserReviewList(String userEmail);
}
