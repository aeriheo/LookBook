package com.pjt2.lb.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginPostRes {
	
	int statusCode;
	String message;
	String accessToken;
	String refreshToken;
	
}
