package com.pjt2.lb.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.BestReviewInfoRes;
import com.pjt2.lb.response.BookInfoGetRes;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.response.MainBookListInfoRes;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.BookService;
import com.pjt2.lb.service.ReviewService;

@CrossOrigin(
        origins = {"http://localhost:3000", "https://j5a502.p.ssafy.io/"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RequestMapping("/books")
@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/{bookIsbn}")
	public ResponseEntity<?> getBookInfo(Authentication authentication, @PathVariable(required = true) String bookIsbn){
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			
			BookInfoGetRes bookInfo = bookService.getBookInfo(bookIsbn, user);
			return ResponseEntity.status(200).body(bookInfo);
		} catch (NullPointerException e) {
			
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
		
	}
	
	@GetMapping()
	public ResponseEntity<?> getSearchBookInfo(Authentication authentication, @RequestParam String searchKey, @RequestParam String searchWord){

		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			
			List<BookListInfoRes> searchBookList = bookService.getSearchBookInfo(searchKey, searchWord);
			Map<String, List> map = new HashMap<String, List>();
			map.put("searchBookList", searchBookList);
			
			return ResponseEntity.status(200).body(map);
		} catch (NullPointerException e) {
			
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
	}
	
	@GetMapping("/main")
	public ResponseEntity<?> getMainBookInfo(Authentication authentication) {
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();

			List<BookListInfoRes> bestBookList = bookService.getBestBookListInfo();	// (1) 베스트 10, 
			BestReviewInfoRes bestReview = reviewService.getBestReviewInfo();			// (2) 베스트 리뷰
			
			MainBookListInfoRes mainBookList = new MainBookListInfoRes(bestBookList, bestReview);
			
			return ResponseEntity.status(200).body(mainBookList);
		} catch(NullPointerException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
	}
}
