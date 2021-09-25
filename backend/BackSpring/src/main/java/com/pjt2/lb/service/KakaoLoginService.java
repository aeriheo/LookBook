package com.pjt2.lb.service;

import com.pjt2.lb.request.KakaoOAuthToken;
import com.pjt2.lb.request.KakaoProfile;

public interface KakaoLoginService {
	
	KakaoOAuthToken getKakaoTokenApi(String code);
	
	KakaoProfile getUserByAccessToken(String accessToken);
}
