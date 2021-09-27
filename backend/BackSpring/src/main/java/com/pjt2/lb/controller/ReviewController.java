package com.pjt2.lb.controller;

import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.pjt2.lb.request.ReviewInfoReq;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.response.BookReviewListInfoRes;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.response.UserReviewListInfoRes;
import com.pjt2.lb.service.ReviewService;

@CrossOrigin(
        origins = {"http://localhost:3000"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RequestMapping("/reviews")
@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	
	@PostMapping()
	public ResponseEntity<?> insertReview(Authentication authentication, @RequestBody ReviewInfoReq reviewInfo) {
		// RequestBody 정보 확인
		String bookIsbn = reviewInfo.getBookIsbn();
		String reviewContent = reviewInfo.getReviewContent();
		System.out.println("bookIsbn: " + bookIsbn + "\nreviewContent" + reviewContent);

		try {
			User user;	// 매개변수로 전달할 사용자 객체 선언
			
			// 사용자 정보 조회
			try { // Authentication에서 받아온 토큰 값이 유효할 때
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser(); // 사용자 정보 가져오기 성공
			} catch (NullPointerException e) { // Authentication에서 받아온 토큰 값이 유효하지 않을 때
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
	
			String userEmail = user.getUserEmail();
			
			// 리뷰 추가
			reviewService.insertReview(userEmail, bookIsbn, reviewContent);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "리뷰 작성 성공"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<?> deleteReview(Authentication authentication, @PathVariable int reviewId) {
		// PathVariable 정보 확인
		System.out.println("reviewId: " + reviewId);

		try {
			User user;	// 매개변수로 전달할 사용자 객체 선언
			
			// 사용자 정보 조회
			try { // Authentication에서 받아온 토큰 값이 유효할 때
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser(); // 사용자 정보 가져오기 성공
			} catch (NullPointerException e) { // Authentication에서 받아온 토큰 값이 유효하지 않을 때
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
			
			// 리뷰 삭제
			reviewService.deleteReview(reviewId);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "리뷰 삭제 성공"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	
	@PutMapping()
	public ResponseEntity<?> updateReview(Authentication authentication, @RequestBody ReviewInfoReq reviewInfo) {
		// RequestBody 정보 확인
		int reviewId = reviewInfo.getReviewId();
		String bookIsbn = reviewInfo.getBookIsbn();
		String reviewContent = reviewInfo.getReviewContent();
		System.out.println("reviewId: " + reviewId + "\nbookIsbn: " + bookIsbn + "\nreviewContent" + reviewContent);

		try {
			User user;	// 매개변수로 전달할 사용자 객체 선언
			
			// 사용자 정보 조회
			try { // Authentication에서 받아온 토큰 값이 유효할 때
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser(); // 사용자 정보 가져오기 성공
			} catch (NullPointerException e) { // Authentication에서 받아온 토큰 값이 유효하지 않을 때
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
			
			// 리뷰 삭제
			reviewService.updateReview(reviewId, bookIsbn, reviewContent);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "리뷰 수정 성공"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> getUserReviewList(Authentication authentication) {
		try {
			User user;	// 매개변수로 전달할 사용자 객체 선언
			
			// 사용자 정보 조회
			try { // Authentication에서 받아온 토큰 값이 유효할 때
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser(); // 사용자 정보 가져오기 성공
			} catch (NullPointerException e) { // Authentication에서 받아온 토큰 값이 유효하지 않을 때
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
			
			String userEmail = user.getUserEmail();
			
			// 사용자 리뷰 리스트 조회
			List<UserReviewListInfoRes> userReviewList = reviewService.getUserReviewList(userEmail);

			Map<String, List> map = new HashMap<String, List>();
			map.put("UserReviewList", userReviewList);
			
			return ResponseEntity.status(200).body(map);
		} catch(Exception e) {	// IllegalArgumentException
			e.printStackTrace();
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}

}
