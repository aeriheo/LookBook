package com.pjt2.lb.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Review;
import com.pjt2.lb.entity.ReviewLike;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.ReviewLikeRepository;
import com.pjt2.lb.repository.ReviewRepository;

@Service
public class ReviewLikeServiceImpl implements ReviewLikeService {
	
	private static final int SUCCESS = 1;
    private static final int FAIL = -1;
    
    @Autowired
    ReviewLikeRepository reviewLikeRepository;
    
    @Autowired
    ReviewRepository reviewRepository;

	@Override
	public int insertReviewLike(User user, int reviewId) {

		try {
			Review review = reviewRepository.findById(reviewId);
			review.setReviewLikeCnt(review.getReviewLikeCnt()+1);
			ReviewLike reviewLike = new ReviewLike();
			reviewLike.setUser(user);
			reviewLike.setReview(review);
			reviewLikeRepository.save(reviewLike);
			return SUCCESS;
			
		} catch (Exception e) {
			return FAIL;
		}
	}

	@Transactional
	@Override
	public int deleteReviewLike(User user, int reviewId) {
		try {
			Review review = reviewRepository.findById(reviewId);
			review.setReviewLikeCnt(review.getReviewLikeCnt()-1);
			reviewLikeRepository.deleteByReviewReviewIdAndUserUserEmail(reviewId, user.getUserEmail());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
	}

}
