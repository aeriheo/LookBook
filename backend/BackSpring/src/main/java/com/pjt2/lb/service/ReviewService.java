package com.pjt2.lb.service;

import com.pjt2.lb.entity.Review;

public interface ReviewService {
	int insertReview(String userEmail, String bookIsbn, String reviewContent);
	int deleteReview(int reviewId);
	int updateReview(int reviewId, String bookIsbn, String reviewContent);
}
