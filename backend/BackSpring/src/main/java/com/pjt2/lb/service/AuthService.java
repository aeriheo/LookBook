package com.pjt2.lb.service;

import com.pjt2.lb.request.TokenPostReq;
import com.pjt2.lb.request.UserLoginPostReq;
import com.pjt2.lb.response.KakaoLoginRes;
import com.pjt2.lb.response.TokenPostRes;
import com.pjt2.lb.response.UserLoginPostRes;

public interface AuthService {
	UserLoginPostRes login(UserLoginPostReq loginInfo);
	TokenPostRes reissue(TokenPostReq refreshToken);
	KakaoLoginRes kakaologin(String email);
}
