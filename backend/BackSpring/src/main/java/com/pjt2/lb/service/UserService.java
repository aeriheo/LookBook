package com.pjt2.lb.service;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.UserRegisterPostReq;
import com.pjt2.lb.response.UserInfoGetRes;

public interface UserService {
	User registerUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserEmail(String userEmail);
	User getUserByUserNickname(String userNickname);
	User getUserByRefreshToken(String refreshToken);
	UserInfoGetRes getUserInfo(User user);
	
}
