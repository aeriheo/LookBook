package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
