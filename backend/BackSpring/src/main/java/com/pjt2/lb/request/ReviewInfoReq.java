package com.pjt2.lb.request;

import lombok.Data;

@Data
public class ReviewInfoReq {
	int reviewId;
	String bookIsbn;
	String reviewContent;
}
