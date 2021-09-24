package com.pjt2.lb.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginPostRes {
	
	String refreshToken;
	String accessToken;
	String message;
	int statusCode;
	
	public UserLoginPostRes(int statusCode, String message, String accessToken, String refreshToken) {
		this.refreshToken = refreshToken;
		this.accessToken = accessToken;
		this.message = message;
		this.statusCode = statusCode;
	}
	
	
}
