package com.pjt2.lb.response;

import lombok.Data;

@Data
public class KakaoLoginRes {
	
	private int statusCode;
	private String message;
	private String email;
	// true : 회원가입, false : 로그인 진행
	private boolean actionCode;
	private String accessToken;
	private String refreshToken;
	private int bookGradeListLength;
}
