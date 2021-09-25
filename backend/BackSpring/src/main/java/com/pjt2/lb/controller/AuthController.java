package com.pjt2.lb.controller;

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
import com.pjt2.lb.request.TokenPostReq;
import com.pjt2.lb.request.UserLoginPostReq;
import com.pjt2.lb.response.TokenPostRes;
import com.pjt2.lb.response.UserLoginPostRes;
import com.pjt2.lb.service.AuthService;
import com.pjt2.lb.service.UserService;

@RequestMapping("/auth")
@RestController
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/hello")
	public String hello() {
		String message = "Hello My name is AuthController";
		return message;
	}

	@PostMapping("/reissue")
	public ResponseEntity<TokenPostRes> reissue(@RequestBody TokenPostReq refreshToken){
		return ResponseEntity.status(200).body(authService.reissue(refreshToken));
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginPostRes> login(@RequestBody UserLoginPostReq loginInfo) {
		return ResponseEntity.status(200).body(authService.login(loginInfo));
	}
}



























