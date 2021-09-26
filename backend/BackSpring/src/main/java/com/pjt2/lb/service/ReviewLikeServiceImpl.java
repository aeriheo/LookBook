package com.pjt2.lb.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.BookGrade;
import com.pjt2.lb.entity.ReviewLike;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.ReviewLikeRepository;

@Service
public class ReviewLikeServiceImpl implements ReviewLikeService {
	
	private static final int SUCCESS = 1;
    private static final int FAIL = -1;
    
    @Autowired
    ReviewLikeRepository reviewLikeRepository;

	@Override
	public int insertReviewLike(User user, int reviewId) {

		try {
			ReviewLike reviewLike = new ReviewLike();
			reviewLike.setUser(user);
			
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
			reviewLikeRepository.deleteByReviewReviewIdAndUserUserEmail(reviewId, user.getUserEmail());
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
	}

}
