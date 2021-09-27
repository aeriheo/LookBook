package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.UserInfoPutReq;
import com.pjt2.lb.request.UserRegisterPostReq;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.UserService;

@CrossOrigin(
        origins = {"http://localhost:3000"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RequestMapping("/users")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// 회원가입
	@PostMapping()
	public ResponseEntity<BaseResponseBody> register(@RequestBody UserRegisterPostReq registerInfo){
		User userGetByEmail = userService.getUserByUserEmail(registerInfo.getUserEmail());
		User userGetByNickname = userService.getUserByUserNickname(registerInfo.getUserNickname());
		if (userGetByEmail != null && userGetByNickname != null) {
			return ResponseEntity.status(411).body(BaseResponseBody.of(411, "Email과 닉네임 모두 사용중입니다."));
		}
		else if (userGetByEmail != null) {
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 가입된 Email입니다."));
		}
		else if (userGetByNickname != null) {
			return ResponseEntity.status(410).body(BaseResponseBody.of(410, "다른 회원이 사용하고 계신 닉네임입니다."));
		}
		else {
			User user = userService.registerUser(registerInfo);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "환영합니다. 회원가입에 성공하셨습니다."));
		}	
	}
	
	// 내 정보 조회
	@GetMapping("/me")
	public ResponseEntity<UserInfoGetRes> getUserInfo(Authentication authentication){
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			UserInfoGetRes userInfo = userService.getUserInfo(user);
			return ResponseEntity.status(200).body(userInfo);
		} catch (NullPointerException e) {
			// 만료된 토큰일 경우 NullPointerException 발생
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
	}
	
	// 이메일 중복 체크
	@GetMapping("/email/{userEmail}")
	public ResponseEntity<BaseResponseBody> checkDuplicatedEmail(@PathVariable String userEmail){
		User user = userService.getUserByUserEmail(userEmail);
		if (user != null) {
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 가입된 Email입니다."));
		}
		else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용할 수 있는 Email입니다."));			
		}
	}
	
	// 닉네임 중복 체크 (회원가입 시)
	@GetMapping("/nickname/{userNickname}")
	public ResponseEntity<BaseResponseBody> checkDuplicatedNickname(@PathVariable String userNickname){
		User user = userService.getUserByUserNickname(userNickname);
		if (user != null) {
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "다른 회원이 사용중인 닉네임입니다."));
		}
		else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용할 수 있는 닉네임입니다."));	
		}
	}
	
	// 닉네임 중복 체크 (가입 후 회원정보 수정 시)
	@GetMapping("/me/nickname/{userNickname}")
	public ResponseEntity<BaseResponseBody> checkDuplicatedNicknameUpdate (Authentication authentication, @PathVariable String userNickname){
		User user = userService.getUserByUserNickname(userNickname);
		LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
		String nickName = userDetails.getUser().getUserNickname();
		if (user != null) {
			if(!nickName.equals(userNickname)) {
				return ResponseEntity.status(409).body(BaseResponseBody.of(409, "다른 회원이 사용중인 닉네임입니다."));	
			}
			else {				
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "현재 회원님이 사용중인 닉네임입니다. (사용할 수 있는 닉네임입니다.)"));	
			}
		}
		else {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용할 수 있는 닉네임입니다."));	
		}
	}
	
	// 회원 탈퇴
	@DeleteMapping()
	public ResponseEntity<BaseResponseBody> deleteUser (Authentication authentication){
		LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
		String userEmail = userDetails.getUsername();
		
		if (userService.deleteUser(userEmail) == 1) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원탈퇴에 성공하셨습니다."));
		}
		else {
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "회원탈퇴중에 문제가 발생하였습니다."));			
		}
	}
	
	// 내 정보 수정
	@PutMapping("/me")
	public ResponseEntity<BaseResponseBody> updateUserInfo (Authentication authentication, @RequestBody UserInfoPutReq userUpdateInfo){
		LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
		User user = userDetails.getUser();
		
		if (userService.update(user, userUpdateInfo) == 1) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "내 정보가 수정되었습니다."));			
		}
		else if (userService.update(user, userUpdateInfo) == 2) {
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "중복된 닉네임입니다."));
		}
		else {
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "업데이트 과정에서 문제가 발생했습니다."));
		}
	}
	
	// 내 프로필 사진 수정
	@PutMapping("/profile")
	public ResponseEntity<BaseResponseBody> updateUserProfile (Authentication authentication, @RequestBody String userProfileUrl){
		LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
		User user = userDetails.getUser();
		
		if (userService.updateProfile(user, userProfileUrl) == 1) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "프로필 사진이 수정되었습니다."));						
		}
		else {
			return ResponseEntity.status(400).body(BaseResponseBody.of(400, "프로필 사진 업데이트 과정에서 문제가 발생했습니다."));				
		}
	}
}
