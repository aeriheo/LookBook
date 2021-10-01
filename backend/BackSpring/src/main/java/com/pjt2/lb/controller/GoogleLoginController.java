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
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.request.GoogleLoginPostReq;
import com.pjt2.lb.response.UserLoginPostRes;
import com.pjt2.lb.service.UserService;

@CrossOrigin(
        origins = {"http://localhost:3000", "https://j5a502.p.ssafy.io/"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RestController
public class GoogleLoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/google/login")
	public ResponseEntity<?> login(@RequestBody GoogleLoginPostReq info) {
		try {
			User user = userService.getUserByUserEmail(info.getEmail());
			String userEmail = user.getUserEmail();
			System.out.println(userEmail);
			
			String accessToken = JwtTokenUtil.getToken(userEmail);
			String refreshToken = JwtTokenUtil.getRefreshToken();
			
			user.setRefreshToken(refreshToken);
			userRepository.save(user);
			
			return ResponseEntity.status(200).body(new UserLoginPostRes(200, "로그인에 성공하였습니다.", accessToken, refreshToken));
			
		} catch (NullPointerException e) {
			return ResponseEntity.status(404).body(new UserLoginPostRes(404, "존재하지 않는 계정입니다.", null, null));
		}
	}
}
