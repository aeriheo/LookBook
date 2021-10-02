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
import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.BestReviewInfoRes;
import com.pjt2.lb.response.BookInfoGetRes;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.response.MainBookListInfoRes;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.BookService;
import com.pjt2.lb.service.ReviewService;

@CrossOrigin(
		origins = { "http://localhost:3000", "https://j5a502.p.ssafy.io/" },
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping("/books")
@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/{bookIsbn}")
	public ResponseEntity<?> getBookInfo(Authentication authentication,
			@PathVariable(required = true) String bookIsbn) {
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();

			BookInfoGetRes bookInfo = bookService.getBookInfo(bookIsbn, user);

			return ResponseEntity.status(200).body(bookInfo);

		} catch (NullPointerException e) {
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "도서 정보가 존재하지 않습니다."));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}

	}

	@GetMapping()
	public ResponseEntity<?> getSearchBookInfo(Authentication authentication, @RequestParam String searchKey,
			@RequestParam String searchWord) {

		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();

			List<BookListInfoRes> searchBookList = bookService.getSearchBookInfo(searchKey, searchWord);
			Map<String, List> map = new HashMap<String, List>();
			map.put("searchBookList", searchBookList);

			return ResponseEntity.status(200).body(map);

		} catch (NullPointerException e) {
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getCategoryList(Authentication authentication,
			@PathVariable(required = true) int categoryId) {
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			
			List<BookListInfoRes> categoryList = bookService.getCategoryList(categoryId, user.getUserEmail());
			Map<String, List> map = new HashMap<String, List>();
			map.put("categoryList", categoryList);
			return ResponseEntity.status(200).body(map);

		} catch (NullPointerException e) {
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}

	}

	@GetMapping("/main")
	public ResponseEntity<?> getMainBookInfo(Authentication authentication) {
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();

			List<BookListInfoRes> bestBookList = bookService.getBestBookListInfo();	// (1) 베스트 10, 
			BestReviewInfoRes bestReview = reviewService.getBestReviewInfo();			// (2) 베스트 리뷰
			
			// CF: 유저 기반 추천 - (3) 사용자 선호도
			// List<UserPredictedGradeModel> userPredictedGradeLis;
			// CF: 유저 기반 추천 - (4) 다른 사람들이 읽은 책
			// List<UserBasedCFModel> userBasedCFList;
			// CF: 아이템 기반 추천 - (5) Best 1도서와 비슷한 책 
			// List<ItemBasedCFModel> itemBasedCFList;                        
			
			
			// MainBookListInfoRes mainBookListInfo = new MainBookListInfoRes(bestBookList, bestReview);
			
			// return ResponseEntity.status(200).body(mainBookListInfo);
			return ResponseEntity.status(200).body("mainBookListInfo");
		} catch(NullPointerException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
	}

}
