package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.dao.ReviewDao;
import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.Review;
import com.pjt2.lb.repository.BookGradeRepositorySupport;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.ReviewRepository;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.response.UserReviewListInfoRes;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	BookGradeRepositorySupport bookGradeRepositorySupport;
	
	@Autowired
	ReviewDao reviewDao;
	
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
			// e.printStackTrace();
			System.out.println("작성되지 않은 리뷰 혹은 bookIsbn 입니다.");
			return -1;
		}
	}
	
	@Override
	public int deleteReview(int reviewId) {
		try {
			reviewRepository.deleteById(reviewId);
			
			return 1;
		} catch(Exception e) {
			// e.printStackTrace();
			System.out.println("존재하지 않는 reviewId 입니다.");
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
			// e.printStackTrace();
			System.out.println("존재하지 않는 reviewId 입니다.");
			return -1;
		}
	}

	@Override
	public List<UserReviewListInfoRes> getUserReviewList(String userEmail) {
		// 사용자가 작성한 리뷰가 없으면 빈 리스트 반환 
		return reviewDao.getUserReviewList(userEmail);
	}

}
