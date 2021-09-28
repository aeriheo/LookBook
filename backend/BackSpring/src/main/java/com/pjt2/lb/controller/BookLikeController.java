package com.pjt2.lb.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.BookLikeService;

@CrossOrigin(
        origins = {"http://localhost:3000", "https://j5a502.p.ssafy.io/"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RequestMapping("/likes")
@RestController
public class BookLikeController {

	@Autowired
	BookLikeService bookLikeService;
	
	@PostMapping("/{bookIsbn}")
	public ResponseEntity<?> addLike(Authentication authentication, @PathVariable String bookIsbn){

		try {
			User user;
			
			try {
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser();
			} catch (NullPointerException e) {
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}

			bookLikeService.addLike(user, bookIsbn);
			
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 추가 성공"));
		} catch(Exception e) {	// IllegalArgumentException
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	
	@DeleteMapping("/{bookIsbn}")
	public ResponseEntity<?> deleteLike(Authentication authentication, @PathVariable String bookIsbn){
		try {
			User user;

			try {
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser();
			} catch (NullPointerException e) {
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}

			bookLikeService.deleteLike(user, bookIsbn);
			
			// 1이면 삭제 성공, 0이면 삭제할 도서가 존재하지 않는다.
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "좋아요 삭제 성공"));
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> getLikeList(Authentication authentication) {
		try {
			User user;

			try {
				LBUserDetails userDetails = (LBUserDetails) authentication.getDetails(); 
				user = userDetails.getUser();
			} catch (NullPointerException e) {
				return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
			}
			
			List<BookListInfoRes> likeBookList = bookLikeService.getLikeBookList(user);

			Map<String, List> map = new HashMap<String, List>();
			map.put("likeBookList", likeBookList);
			
			return ResponseEntity.status(200).body(map);
		} catch(Exception e) {
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Internal Server Error"));
		}
	}

}
