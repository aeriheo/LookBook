package com.pjt2.lb.service;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.UserInfoGetRes;

public interface UserService {
	User getUserByUserEmail(String userEmail);
	User getUserByuserNickname(String userNickname);
	UserInfoGetRes getUserInfo(User user);
}
