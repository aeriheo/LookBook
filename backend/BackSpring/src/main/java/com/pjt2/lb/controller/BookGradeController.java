package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.request.BookGradePostReq;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.BookGradeService;

@CrossOrigin(
        origins = {"http://localhost:3000"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RequestMapping("/bookgrade")
@RestController
public class BookGradeController {
	
	@Autowired
	BookGradeService bookGradeService;
	
	@Autowired
	BookRepository bookRepository;
	
	@PostMapping()
	public ResponseEntity<?> insertBookGrade(Authentication authentication, @RequestBody BookGradePostReq bookGradePostReq){
		
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			
			int result = 0;
			result = bookGradeService.insertBookGrade(user, bookGradePostReq);
			if(result == 1) {			
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "평점 입력 성공"));
			} else {
				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "평점 입력 실패"));
			}
		} catch (NullPointerException e) {
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}

		
	}
	
	@PutMapping()
	public ResponseEntity<?> updateBookGrade(Authentication authentication, @RequestBody BookGradePostReq bookGradePostReq){
		
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			
			int result = 0;
			result = bookGradeService.updateBookGrade(user, bookGradePostReq);
			if(result == 1) {			
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "평점 수정 성공"));
			} else {
				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "평점 수정 실패"));
			}
		} catch (NullPointerException e) {
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}

		
	}
}
