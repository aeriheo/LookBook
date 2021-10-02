package com.pjt2.lb.response;

import java.util.List;

import com.pjt2.lb.entity.ItemBasedCFModel;
import com.pjt2.lb.entity.UserBasedCFModel;
import com.pjt2.lb.entity.UserPredictedGradeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainBookListInfoRes {
	List<BookListInfoRes> bestBookList;
	BestReviewInfoRes bestReview;
	
	// CF: 유저 기반 추천 - (3) 사용자 선호도
	List<UserPredictedGradeModel> userPredictedGradeList;
	// CF: 유저 기반 추천 - (4) 다른 사람들이 읽은 책
	List<UserBasedCFModel> userBasedCFList;
	// CF: 아이템 기반 추천 - (5) Best 1도서와 비슷한 책 
	List<ItemBasedCFModel> itemBasedCFList;
}
