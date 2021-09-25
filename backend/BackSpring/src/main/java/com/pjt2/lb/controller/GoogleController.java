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
@RequestMapping("v1/google")
public class GoogleController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody GoogleLoginPostReq info) {
		System.out.println("구글에서 로그인에 성공한 이메일: " + info.getEmail());
		
		
		try {
			// 유정 정보가 있으면 로그인 성공
			User user = userService.getUserByUserEmail(info.getEmail());
			String userEmail = user.getUserEmail();
			System.out.println(userEmail);
			
			String accessToken = JwtTokenUtil.getToken(userEmail);	// 로그인 여부를 토큰으로 판단한다.
			String refreshToken = JwtTokenUtil.getRefreshToken();	// 리프레시 토큰은 인자 필요없다.
			
			return ResponseEntity.status(200).body(new UserLoginPostRes(200, "로그인에 성공하였습니다.", accessToken, refreshToken));
			
		} catch (NullPointerException e) {	// 로그인 실패
			return ResponseEntity.status(404).body(new UserLoginPostRes(404, "존재하지 않는 계정입니다.", null, null));
		}
	}
}
