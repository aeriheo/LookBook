package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.KakaoOAuthToken;
import com.pjt2.lb.request.KakaoProfile;
import com.pjt2.lb.response.KakaoLoginRes;
import com.pjt2.lb.service.AuthService;
import com.pjt2.lb.service.KakaoLoginService;
import com.pjt2.lb.service.UserService;


@CrossOrigin(
        origins = {"http://localhost:3000", "https://j5a502.p.ssafy.io/"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RestController
public class KakaoLoginController {
	
	@Autowired
	KakaoLoginService kakaoLoginService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;
	
	 @GetMapping("/kakao/login")
	 public ResponseEntity<KakaoLoginRes> oauth2AuthorizationKakao(@RequestParam("code") String code) {
		 
		 KakaoLoginRes kakaoLoginRes = new KakaoLoginRes();
		 
		 // code로 accessToken을 받아내고
		 KakaoOAuthToken kakaoOAuthToken = kakaoLoginService.getKakaoTokenApi(code);
		 // 받은 accessToken으로 회원 정보를 가져온다.
		 KakaoProfile kakaoProfile = kakaoLoginService.getUserByAccessToken(kakaoOAuthToken.getAccess_token());
		 
		 // System.out.println(kakaoProfile);
		 
		 String userEmail = kakaoProfile.getKakao_account().getEmail();
		 kakaoLoginRes.setEmail(userEmail);
		 // System.out.println(kakaoLoginRes.getEmail());			 

		 User user = userService.getUserByUserEmail(userEmail);
		 
		 if(user != null) {
			 kakaoLoginRes = authService.kakaologin(userEmail);
			 return ResponseEntity.status(200).body(kakaoLoginRes);
		 } else {
			 kakaoLoginRes.setActionCode(true);
			 kakaoLoginRes.setStatusCode(200);
			 return ResponseEntity.status(200).body(kakaoLoginRes);
		 }
		 
	 }
	
}
