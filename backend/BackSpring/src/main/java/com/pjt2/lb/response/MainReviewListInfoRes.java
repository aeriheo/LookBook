package com.pjt2.lb.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainReviewListInfoRes {
	private int review_id;
	private String review_content;
	private String review_date;
	private String review_like_count;
	private String user_nickname;
	
}
