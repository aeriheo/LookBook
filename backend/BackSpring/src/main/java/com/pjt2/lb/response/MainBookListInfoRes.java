package com.pjt2.lb.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainBookListInfoRes {
	List<BookListInfoRes> bestBookList;
	BestReviewInfoRes bestReview;
}
