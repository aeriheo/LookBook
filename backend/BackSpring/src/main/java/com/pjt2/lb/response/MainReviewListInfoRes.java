package com.pjt2.lb.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainReviewListInfoRes {
	private int review_id;
	private String review_content;
	private String review_date;
	private int review_like_cnt;
	private String user_nickname;
	
}
