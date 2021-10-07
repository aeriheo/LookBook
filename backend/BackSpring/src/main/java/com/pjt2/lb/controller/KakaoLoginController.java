package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.response.BaseResponseBody;
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
	 public ResponseEntity<?> oauth2AuthorizationKakao(@RequestParam("code") String code) {

		 try {
			 KakaoLoginRes kakaoLoginRes = new KakaoLoginRes();


			 KakaoOAuthToken kakaoOAuthToken = kakaoLoginService.getKakaoTokenApi(code);
			 KakaoProfile kakaoProfile = kakaoLoginService.getUserByAccessToken(kakaoOAuthToken.getAccess_token());
			 
			 String userEmail = kakaoProfile.getKakao_account().getEmail();
			 kakaoLoginRes.setEmail(userEmail);
		 
			 User user = userService.getUserByUserEmail(userEmail);
			 
			 if(user != null) {
				 kakaoLoginRes = authService.kakaologin(userEmail);
				 return ResponseEntity.status(200).body(kakaoLoginRes);
			 } else {
				 kakaoLoginRes.setActionCode(true);
				 kakaoLoginRes.setStatusCode(200);
				 return ResponseEntity.status(200).body(kakaoLoginRes);
			 }
			 
		 } catch (Exception e) {
			 return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		 }
		 
		 
	 }
	
}
