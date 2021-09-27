package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.ReviewLikeService;

@CrossOrigin(
        origins = {"http://localhost:3000"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RestController
@RequestMapping("/reviewlikes")
public class ReviewLikeController {
	
	@Autowired
	ReviewLikeService reviewLikeService;
	
	@PostMapping("/{reviewId}")
	public ResponseEntity<?> insertReviewLike(Authentication authentication, @PathVariable int reviewId){
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			
			int result = 0;
			result = reviewLikeService.insertReviewLike(user, reviewId);
			if(result == 1) {			
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "리뷰 좋아요 입력 성공"));
			} else {
				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "리뷰 좋아요 입력 실패"));
			}
		} catch (NullPointerException e) {
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
		
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?> deleteReviewLike(Authentication authentication, @PathVariable int reviewId){
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();

			int result = 0;
			result = reviewLikeService.deleteReviewLike(user, reviewId);
			if(result == 1) {			
				return ResponseEntity.status(200).body(BaseResponseBody.of(200, "리뷰 좋아요 삭제 성공"));
			} else {
				return ResponseEntity.status(500).body(BaseResponseBody.of(500, "리뷰 좋아요 삭제 실패"));
			}
		} catch (NullPointerException e) {
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
		
	}

}
