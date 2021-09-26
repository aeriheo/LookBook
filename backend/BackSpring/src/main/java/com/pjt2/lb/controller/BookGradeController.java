package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.request.BookGradePostReq;
import com.pjt2.lb.service.BookGradeService;

@RequestMapping("/bookgrade")
@RestController
public class BookGradeController {
	
	@Autowired
	BookGradeService bookGradeService;
	
	@Autowired
	BookRepository bookRepository;
	
	@PostMapping()
	public ResponseEntity<BaseResponseBody> insertBookGrade(@RequestBody BookGradePostReq bookGradePostReq){
		
		String userEmail = "test1@test.com";
		String bookIsbn = bookGradePostReq.getBookIsbn();
		int bookGrade = bookGradePostReq.getBookGrade();
		int result = 0;
		result = bookGradeService.insertBookGrade(userEmail, bookIsbn, bookGrade);
		if(result == 1) {			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "평점 입력 성공"));
		} else {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "평점 입력 실패"));
		}
	}
	
	@PutMapping()
	public ResponseEntity<BaseResponseBody> updateBookGrade(@RequestBody BookGradePostReq bookGradePostReq){
		
		String userEmail = "test1@test.com";
		String bookIsbn = bookGradePostReq.getBookIsbn();
		int bookGrade = bookGradePostReq.getBookGrade();
		int result = 0;
		result = bookGradeService.updateBookGrade(userEmail, bookIsbn, bookGrade);
		if(result == 1) {			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "평점 수정 성공"));
		} else {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "평점 수정 실패"));
		}
	}
}
