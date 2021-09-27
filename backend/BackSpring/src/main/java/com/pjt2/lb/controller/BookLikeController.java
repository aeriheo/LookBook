package com.pjt2.lb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.BookLikeService;


@RequestMapping("/likes")
@RestController
public class BookLikeController {

	@Autowired
	BookLikeService bookLikeService;
	
	
	// 좋아요 추가
	// Authentication 매개변수로 토큰정보 가져오기
	@PostMapping("/{bookIsbn}")
	public ResponseEntity<?> addLike(Authentication authentication, @PathVariable String bookIsbn){
		
		System.out.println("bookIsbn: " + bookIsbn);

		try {
			User user;	// 매개변수로 전달할 사용자 객체 선언
			
			// 사용자 정보 조회
			try { // Authentication에서 받아온 토큰 값이 유효할 때
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser(); // 사용자 정보 가져오기 성공
			} catch (NullPointerException e) { // Authentication에서 받아온 토큰 값이 유효하지 않을 때
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
	
			// 좋아요 추가
			bookLikeService.addLike(user, bookIsbn);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 추가 성공"));
		} catch(Exception e) {	// IllegalArgumentException
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	
	// 좋아요 추가 테스트
	/*
	@PostMapping("/{bookIsbn}")
	public ResponseEntity<?> addLike(@PathVariable String bookIsbn){
		
		System.out.println("bookIsbn: " + bookIsbn);

		String userEmail = "tjalsdud9@gmail.com";
		
		try {
			// 좋아요 추가
			likeService.addLike(userEmail, bookIsbn);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 추가 성공."));
		} catch(Exception e) {	// IllegalArgumentException
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	*/
	
	// 좋아요 삭제
	@DeleteMapping("/{bookIsbn}")
	public ResponseEntity<?> deleteLike(Authentication authentication, @PathVariable String bookIsbn){
		
		System.out.println("bookIsbn: " + bookIsbn);
		
		try {
			User user;	// 매개변수로 전달할 사용자 객체 선언
			
			// 사용자 정보 조회
			try { // Authentication에서 받아온 토큰 값이 유효할 때
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser(); // 사용자 정보 가져오기 성공
			} catch (NullPointerException e) { // Authentication에서 받아온 토큰 값이 유효하지 않을 때
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
	
			// 좋아요 삭제
			bookLikeService.deleteLike(user, bookIsbn);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 삭제 성공"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	
	// 좋아요 삭제 테스트
	/*
	@DeleteMapping("/{bookIsbn}")
	public ResponseEntity<?> deleteLike(@PathVariable String bookIsbn){
		
		System.out.println("bookIsbn: " + bookIsbn);
		
		try {
			String userEmail = "tjalsdud9@gmail.com";
			
			// 좋아요 삭제
			likeService.deleteLike(userEmail, bookIsbn);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 삭제 성공."));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	*/
	
	
	// 좋아요 리스트
	@GetMapping()
	public ResponseEntity<?> getLikeList(Authentication authentication) {
		try {
			User user;	// 매개변수로 전달할 사용자 객체 선언
			
			// 사용자 정보 조회
			try { // Authentication에서 받아온 토큰 값이 유효할 때
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser(); // 사용자 정보 가져오기 성공
			} catch (NullPointerException e) { // Authentication에서 받아온 토큰 값이 유효하지 않을 때
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
			
			// 사용자가 좋아요한 도서 리스트 가져오기
			List<BookListInfoRes> likeBookList = bookLikeService.getLikeBookList(user);

			Map<String, List> map = new HashMap<String, List>();
			map.put("likeBookList", likeBookList);
			
			return ResponseEntity.status(200).body(map);
//			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 리스트 조회 성공"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}

	// 좋아요 리스트 테스트
	/*
	@GetMapping()
	public ResponseEntity<?> getLikeList() {
		try {
			String userEmail = "tjalsdud9@gmail.com";
			
			// 사용자가 좋아요한 도서 리스트 가져오기
			List<BookListInfoRes> likeBookList = likeService.getLikeBookList(userEmail);
			
			Map<String, List> map = new HashMap<String, List>();
			map.put("likeBookList", likeBookList);
			
			return ResponseEntity.status(200).body(map);
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	*/
	
}
