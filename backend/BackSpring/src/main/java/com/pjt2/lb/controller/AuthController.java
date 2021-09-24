package com.pjt2.lb.controller;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.util.JwtTokenUtil;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.request.UserLoginPostReq;
import com.pjt2.lb.response.UserLoginPostRes;
import com.pjt2.lb.service.UserService;

@RequestMapping("/auth")
@RestController
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/hello")
	public String hello() {
		String message = "Hello My name is AuthController";
		return message;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginPostRes> login(@RequestBody UserLoginPostReq loginInfo) {
		String userEmail = loginInfo.getUserEmail();
		String userPassword = loginInfo.getUserPassword();
		
		try {
			
			User user = userService.getUserByUserEmail(userEmail);
			
			String accessToken = JwtTokenUtil.getToken(userEmail);
			String refreshToken = JwtTokenUtil.getRefreshToken(userEmail);
//			System.out.println("accessToken : " + JwtTokenUtil.getToken(userEmail));
//			System.out.println("refreshToken : " + JwtTokenUtil.getRefreshToken(userEmail));
//			JwtTokenUtil.handleError(accessToken);
			
			if (passwordEncoder.matches(userPassword, user.getUserPassword())) {
				user.setRefreshToken(refreshToken);
				userRepository.save(user);
				return ResponseEntity.status(200).
						body(new UserLoginPostRes(200, "로그인에 성공하였습니다.", accessToken, refreshToken));
			}
			else {
				return ResponseEntity.status(401).body(new UserLoginPostRes(401,"잘못된 비밀번호 입니다.", null, null));
			}
		} catch(NullPointerException e) {
			return ResponseEntity.status(404).body(new UserLoginPostRes(404, "존재하지 않는 계정입니다.", null, null));
		}
	}
	
}



























