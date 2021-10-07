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
	List<BookListInfoRes> userPredictedGradeList;
	List<BookListInfoRes> userBasedCFList;
	List<BookListInfoRes> itemBasedCFList;
}
