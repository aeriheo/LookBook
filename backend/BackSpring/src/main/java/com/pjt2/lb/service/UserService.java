package com.pjt2.lb.service;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.UserInfoPutReq;
import com.pjt2.lb.request.UserProfilePostReq;
import com.pjt2.lb.request.UserRegisterPostReq;
import com.pjt2.lb.response.UserInfoGetRes;

public interface UserService {
	User registerUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserEmail(String userEmail);
	User getUserByUserNickname(String userNickname);
	User getUserByRefreshToken(String refreshToken);
	UserInfoGetRes getUserInfo(User user);
	int deleteUser(String userEmail);
	int update(User user, UserInfoPutReq userUpdateInfo);
	int updateProfile(User user, UserProfilePostReq userProfileInfo);
	
}
