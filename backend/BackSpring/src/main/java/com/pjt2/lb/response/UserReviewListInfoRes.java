package com.pjt2.lb.response;

import lombok.Data;

@Data
public class UserReviewListInfoRes {
	int reviewId;				// Review - 리뷰 아이디
	String reviewContent;		// Review - 리뷰 내용
	String reviewDate;			// Review - 리뷰 작성 일자
	int reviewLikeCount;		// Review - 리뷰 좋아요 개수
	String userNickname;		// User - 유저 닉네임
	int bookGrade;				// BookGrade - 도서 평점
	String bookIsbn;			// Book - 도서 ISBN
	String bookTitle;			// Book - 도서 제목
	String bookImgUrl;			// Book - 도서 이미지
}
