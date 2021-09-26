package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.service.LikeService;


@RequestMapping("/likes")
@RestController
public class LikeController {

	@Autowired
	LikeService likeService;
	
	
	// 좋아요 추가
	@PostMapping("/{bookIsbn}")
	public ResponseEntity<BaseResponseBody> addLike(@PathVariable String bookIsbn){
		
		System.out.println("bookIsbn: " + bookIsbn);
		
		// 이메일로 확인해서 사용자 확인한 후,
		// 좋아요 확인하기
		
//		likeService.addLike(bookIsbn);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 추가 성공."));
//		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
	}
	
	// 좋아요 삭제
	@DeleteMapping("/{bookIsbn}")
	public ResponseEntity<BaseResponseBody> deleteLike(@PathVariable String bookIsbn){
		
		System.out.println("bookIsbn: " + bookIsbn);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 삭제 성공."));
//		return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
	}
	
	// 좋아요 리스트
//	@GetMapping()
//	public ResponseEntity<List<Book>> getLikeList() {
//		return return ResponseEntity.status(200).body();
//	}
	
	
	
	
	
}
