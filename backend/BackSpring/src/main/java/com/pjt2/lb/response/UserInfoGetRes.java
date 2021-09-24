package com.pjt2.lb.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoGetRes {
	String userEmail;
	String userPassword;
	String userName;
	String userNickname;
	String userProfileUrl;
	String accessToken;
}
