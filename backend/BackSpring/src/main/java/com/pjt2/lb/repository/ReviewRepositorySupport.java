package com.pjt2.lb.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.QReview;
import com.pjt2.lb.entity.QUser;
import com.pjt2.lb.entity.Review;
import com.pjt2.lb.response.MainReviewListInfoRes;
import com.pjt2.lb.response.UserReviewListInfoRes;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ReviewRepositorySupport {
	
	@Autowired
	private JPAQueryFactory query;
	
	QReview qReview = QReview.review;
	QUser qUser = QUser.user;
	
	public List<Review> getUserReviewList(String userEmail){
		// 사용자가 작성한 도서의 bookIsbn list
		List<Review> review = query.selectFrom(qReview).where(qReview.user.userEmail.eq(userEmail)).fetch();
		System.out.println(review);
		return review;
	}
	
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
