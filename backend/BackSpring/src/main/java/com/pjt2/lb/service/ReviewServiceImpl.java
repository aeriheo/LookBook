package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Review;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.ReviewRepository;
import com.pjt2.lb.repository.ReviewRepositorySupport;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.response.MainReviewListInfoRes;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReviewRepositorySupport reviewRepositorySupport;
	
	@Override
	public int insertReview(String userEmail, String bookIsbn, String reviewContent) {
		try {
			Review review = new Review();
			review.setBook(bookRepository.findByBookIsbn(bookIsbn));
			review.setUser(userRepository.findUserByUserEmail(userEmail));
			review.setReviewContent(reviewContent);
			reviewRepository.save(review);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public int deleteReview(int reviewId) {
		try {
			reviewRepository.deleteById(reviewId);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public int updateReview(int reviewId, String bookIsbn, String reviewContent) {
		try {
			Review review = reviewRepository.findById(reviewId);
			review.setReviewContent(reviewContent);
			reviewRepository.save(review);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<MainReviewListInfoRes> getMainReviewList(String bookIsbn) {
		return reviewRepositorySupport.getMainReviewList(bookIsbn);
	}
	
}
