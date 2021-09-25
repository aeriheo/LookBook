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
import com.pjt2.lb.response.GoogleRes;
import com.pjt2.lb.response.UserLoginPostRes;
import com.pjt2.lb.service.UserService;

@CrossOrigin(
        origins = {"http://localhost:3000/"}, 
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
) 
@RestController
@RequestMapping("v1/test/google")
public class googleController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/email")
	public ResponseEntity<?> login(@RequestBody String email) {
		System.out.println(email);
		
		try {
			// 유정 정보가 있으면 로그인 성공
			User user = userService.getUserByUserEmail(email);
			
			// 로그인 여부를 토큰으로 판단한다.
			String accessToken = JwtTokenUtil.getToken(email);
			// 리프레시 토큰은 인자 필요없다.
//			String refreshToken = JwtTokenUtil.getRefreshToken();
			
			
			// return 토큰 정보 프론트로 보내주기;
			
		} catch (NullPointerException e) {
			// 로그인 실패
//			return ResponseEntity.status(404).body(new Object(0));
		}
		
		return null;
	}
}
