package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.util.JwtTokenUtil;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.GoogleLoginPostReq;
import com.pjt2.lb.response.UserLoginPostRes;
import com.pjt2.lb.service.UserService;

@CrossOrigin(
        origins = {"http://localhost:3000/"}, 
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
) 
@RestController
public class GoogleController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login/google")
	public ResponseEntity<?> login(@RequestBody GoogleLoginPostReq info) {
		System.out.println("구글에서 로그인에 성공한 이메일: " + info.getEmail());
		
		try {
			User user = userService.getUserByUserEmail(info.getEmail());
			String userEmail = user.getUserEmail();
			System.out.println(userEmail);
			
			String accessToken = JwtTokenUtil.getToken(userEmail);
			String refreshToken = JwtTokenUtil.getRefreshToken();
			
			return ResponseEntity.status(200).body(new UserLoginPostRes(200, "로그인에 성공하였습니다.", accessToken, refreshToken));
			
		} catch (NullPointerException e) {	// 로그인 실패
			return ResponseEntity.status(404).body(new UserLoginPostRes(404, "존재하지 않는 계정입니다.", null, null));
		}
	}
}
