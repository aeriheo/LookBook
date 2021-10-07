package com.pjt2.lb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestReviewInfoRes {
	String bookIsbn;			// Book - 도서 ISBN
	String bookTitle;			// Book - 도서 제목
	String bookImgUrl;			// Book - 도서 이미지
	String reviewContent;		// Review - 리뷰 내용
	String userNickname;		// User - 유저 닉네
}
