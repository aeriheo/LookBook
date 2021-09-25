package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.request.KakaoOAuthToken;
import com.pjt2.lb.request.KakaoProfile;
import com.pjt2.lb.response.KakaoRes;
import com.pjt2.lb.service.KakaoLoginService;


@CrossOrigin(
        origins = {"http://localhost:3000"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RestController
public class KakaoLoginController {
	
	@Autowired
	KakaoLoginService kakaoLoginService;
	
	 @GetMapping("/login/kakao")
	 public ResponseEntity<KakaoRes> oauth2AuthorizationKakao(@RequestParam("code") String code) {
		 
		 KakaoRes kakaoRes = new KakaoRes();
		 
		 // code로 accessToken을 받아내고
		 KakaoOAuthToken kakaoOAuthToken = kakaoLoginService.getKakaoTokenApi(code);
		 // 받은 accessToken으로 회원 정보를 가져온다.
		 KakaoProfile kakaoProfile = kakaoLoginService.getUserByAccessToken(kakaoOAuthToken.getAccess_token());
		 
		 kakaoRes.setEmail(kakaoProfile.getKakao_account().getEmail());
//		 System.out.println(kakaoProfile);
		 System.out.println(kakaoRes.getEmail());
				 

		 return new ResponseEntity(kakaoRes, HttpStatus.OK);
		 
		 // DB에서 확인을 해서 이미 존재하는 아이디라면 바로 로그인으로 넘어가고
		 
		 // 아니라면 회원가입 절차를 거쳐야할 듯!
		 
	 }
	
}
