package com.pjt2.lb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.response.BookInfoGetRes;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.service.BookService;

@RequestMapping("/books")
@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping("/{bookIsbn}")
	public ResponseEntity<BookInfoGetRes> getBookInfo(@PathVariable(required = true) String bookIsbn){
//		try {
//			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
//			User user = userDetails.getUser();
//			
//			return ResponseEntity.status(200).body(userInfo);
//		} catch (NullPointerException e) {
//			// 만료된 토큰일 경우 NullPointerException 발생
//			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
//		}
		BookInfoGetRes bookInfo = bookService.getBookInfo(bookIsbn, "test1@test.com");
		return ResponseEntity.status(200).body(bookInfo);
	}
	
	@GetMapping()
	public ResponseEntity<?> getSearchBookInfo(@RequestParam String searchKey, @RequestParam String searchWord){

		List<BookListInfoRes> searchBookList = bookService.getSearchBookInfo(searchKey, searchWord);
		Map<String, List> map = new HashMap<String, List>();
		map.put("searchBookList", searchBookList);
		
		return ResponseEntity.status(200).body(map);
	}
	
}
