package com.pjt2.lb.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginPostReq {
	String userEmail;
	String userPassword;
}
