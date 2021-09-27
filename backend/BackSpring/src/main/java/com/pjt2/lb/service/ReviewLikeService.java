package com.pjt2.lb.service;

import com.pjt2.lb.entity.User;

public interface ReviewLikeService {

	int insertReviewLike(User user, int reviewId);

	int deleteReviewLike(User user, int reviewId);

}
