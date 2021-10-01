package com.pjt2.lb.service;

import java.util.List;

import com.pjt2.lb.entity.Review;
import com.pjt2.lb.response.BestReviewInfoRes;
import com.pjt2.lb.response.UserReviewListInfoRes;

public interface ReviewService {
	int insertReview(String userEmail, String bookIsbn, String reviewContent);
	int deleteReview(int reviewId);
	int updateReview(int reviewId, String bookIsbn, String reviewContent);
	List<UserReviewListInfoRes> getUserReviewList(String userEmail);
	
	BestReviewInfoRes getBestReviewInfo();
}
