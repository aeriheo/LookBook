package com.pjt2.lb.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.QBook;
import com.pjt2.lb.entity.QBookGrade;
import com.pjt2.lb.entity.QReview;
import com.pjt2.lb.entity.QUser;
import com.pjt2.lb.entity.Review;
import com.pjt2.lb.response.MainReviewListInfoRes;
import com.pjt2.lb.response.UserReviewListInfoRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ReviewRepositorySupport {
	
	@Autowired
	private JPAQueryFactory query;
	
	QReview qReview = QReview.review;
	QUser qUser = QUser.user;
	QBook qBook = QBook.book;
	QBookGrade qBookGrade = QBookGrade.bookGrade1;
	
	/*
	// public List<UserReviewListInfoRes> getUserReviewList(String userEmail){
	public List<Object> getUserReviewList(String userEmail){
		
		// DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		return query
				//.select(Projections.bean(UserReviewListInfoRes.class, 
				.select(Projections.bean(Object.class, 
						qReview.reviewId, 
						qReview.reviewContent,
						qReview.reviewDate,
						qReview.reviewLikeCount,
						// qBookGrade.bookGrade,
						qBook.bookIsbn,
						qBook.bookTitle,
						qBook.bookImgUrl))
				.from(qReview)
				.join(qReview.book, qBook)
				.where(qReview.user.userEmail.eq(userEmail))
				.fetch();
	}
	*/
	
	public List<UserReviewListInfoRes> getUserReviewList(String userEmail){
		List<UserReviewListInfoRes> userReviewList = 
				query.select(Projections.bean(UserReviewListInfoRes.class, 
					qReview.reviewId, 
					qReview.reviewContent,
					qReview.reviewDate,
					qReview.reviewLikeCnt,
					qBookGrade.bookGrade,
					qBook.bookIsbn,
					qBook.bookTitle,
					qBook.bookImgUrl))
				.from(qReview)
				.leftJoin(qReview.book, qBook).on(qReview.user.userEmail.eq(userEmail))
				.leftJoin(qBookGrade).on(qBook.bookIsbn.eq(qBookGrade.book.bookIsbn))
				.fetch();
		return userReviewList;
	}
	
	/*
	 query.select(Projections.bean(UserReviewListInfoRes.class, 
			qReview.reviewId, 
			qReview.reviewContent,
			qReview.reviewDate,
			qReview.reviewLikeCount,
			qBookGrade.bookGrade,
			qBook.bookIsbn,
			qBook.bookTitle,
			qBook.bookImgUrl))
		.from(qReview)
		.where(qReview.user.userEmail.eq(userEmail).in(
				JPAExpressions.select(
						qBookGrade.bookGrade,
						qBook.bookIsbn,
						qBook.bookTitle,
						qBook.bookImgUrl)
				.from(qBook, qBookGrade)
				.join(qBook.bookIsbn, qBookGrade.book.bookIsbn)
				.where(qBookGrade.user.userEmail.eq(userEmail))
				))
		.fetch();
	 */
	
	public List<MainReviewListInfoRes> getMainReviewList(String bookIsbn){
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		List<Review> reviewList = query
				.selectFrom(qReview)
				.join(qReview.user, qUser)
				.where(qReview.book.bookIsbn.eq(bookIsbn))
				.fetch();
//		return reviewList.stream()
//				.map(r -> new MainReviewListInfoRes(r.getReviewId(), r.getReviewContent(), dateFormat.format(r.getReviewDate()), r.getReviewLikeCount(), r.getUser().getUserNickname()))
//				.collect(Collectors.toList());
		return null;
	}

}
