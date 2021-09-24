package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.UserRegisterPostReq;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping()
	public ResponseEntity<? extends BaseResponseBody> register(@RequestBody UserRegisterPostReq registerInfo){
		User userGetByEmail = userService.getUserByUserEmail(registerInfo.getUserEmail());
		User userGetByNickname = userService.getUserByuserNickname(registerInfo.getUserNickname());
		if (userGetByEmail != null && userGetByNickname != null) {
			return ResponseEntity.status(411).body(BaseResponseBody.of(411, "Email과 닉네임 모두 사용중입니다."));
		}
		else if (userGetByEmail != null) {
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 가입된 Email입니다."));
		}
		else if (userGetByNickname != null) {
			return ResponseEntity.status(410).body(BaseResponseBody.of(410, "다른 회원이 사용하고 계신 닉네임입니다."));
		}
		else {
			User user = userService.registerUser(registerInfo);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "환영합니다. 회원가입에 성공하셨습니다."));
		}
		
	}
	
	@GetMapping("/me")
	public ResponseEntity<UserInfoGetRes> getUserInfo(Authentication authentication){
		LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
		User user = userDetails.getUser();
		UserInfoGetRes userInfo = userService.getUserInfo(user);
		try {
			return ResponseEntity.status(200).body(userInfo);
		} catch(Exception e) {
			return ResponseEntity.status(404).body(null);
		}
		
	}
}
