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
import com.pjt2.lb.entity.ItemBasedCFModel;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.entity.UserPredictedGradeModel;
import com.pjt2.lb.response.BestReviewInfoRes;
import com.pjt2.lb.response.BookInfoGetRes;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.response.MainBookListInfoRes;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.BookService;
import com.pjt2.lb.service.RecommendBookService;
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
	
	@Autowired
	RecommendBookService recommendBookService;

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

			MainBookListInfoRes mainBookListInfo = new MainBookListInfoRes();
			
			List<BookListInfoRes> bestBookList = bookService.getBestBookListInfo();
			mainBookListInfo.setBestBookList(bestBookList);
			
			BestReviewInfoRes bestReview = reviewService.getBestReviewInfo();
			mainBookListInfo.setBestReview(bestReview);
			
			List<BookListInfoRes> userPredictedGradeList = recommendBookService.getUserPredictedGradeListInfo(user.getUserEmail(), 10);
			mainBookListInfo.setUserPredictedGradeList(userPredictedGradeList);
			
			List<BookListInfoRes> userBasedCFList = recommendBookService.getUserBasedCFListInfo(user.getUserEmail(), 10);
			mainBookListInfo.setUserBasedCFList(userBasedCFList);
			
			List<BookListInfoRes> itemBasedCFList = recommendBookService.getItemBasedCFListInfo(10);                   
			mainBookListInfo.setItemBasedCFList(itemBasedCFList);
			
			 return ResponseEntity.status(200).body(mainBookListInfo);
		} catch(NullPointerException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new UserInfoGetRes(500, "Internal Server Error"));
		}
	}

}
