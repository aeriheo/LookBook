package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Review;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.ReviewRepository;
import com.pjt2.lb.repository.ReviewRepositorySupport;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.response.UserReviewListInfoRes;
import com.pjt2.lb.response.MainReviewListInfoRes;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	// 리뷰 카운트 증가 구현하기
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
	
	// 리뷰 카운트 감소 구현하기
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
	public List<UserReviewListInfoRes> getUserReviewList(String userEmail) {
		// 이메일 사용해서 닉네임 받아오기(userNickname)
		String userNickname = userRepository.findUserByUserEmail(userEmail).getUserEmail();
		
		// 리뷰에서 내가 작성한 리뷰 리스트 받아오기(이때, bookIsbn, reviewId, reviewDate, reviewContent)
		List<Review> reviewList = reviewRepositorySupport.getUserReviewList(userEmail);
		
		// 반환할 UserReviewListInfoRes 리스트
		for(Review review : reviewList) {
			
		}
		
		// 리뷰 리스트를 사용하여 bookIsbn으로 도서 리스트 받아오기(bookTitle, bookImgUrl)
		
		
		// userEmail, bookIsbn 사용하여 내가 부여한 평점 받아오기
		
		// 위에 구현해서 리스트 반환으로 변경하기
		return null;
	}
	
	@Override
	public List<MainReviewListInfoRes> getMainReviewList(String bookIsbn) {
		return reviewRepositorySupport.getMainReviewList(bookIsbn);
	}
	
	
}
