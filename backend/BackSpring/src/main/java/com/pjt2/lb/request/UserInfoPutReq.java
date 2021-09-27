package com.pjt2.lb.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoPutReq {
	String userPassword;
	String userName;
	String userNickname;
}
