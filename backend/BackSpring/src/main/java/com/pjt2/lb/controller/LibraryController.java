package com.pjt2.lb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pjt2.lb.common.auth.LBUserDetails;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.LibraryGetReq;
import com.pjt2.lb.response.LibraryGetRes;
import com.pjt2.lb.response.UserInfoGetRes;
import com.pjt2.lb.service.LibraryService;

@CrossOrigin(
        origins = {"http://localhost:3000", "https://j5a502.p.ssafy.io/"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RequestMapping("/library")
@RestController
public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	
	@GetMapping()
	public ResponseEntity<?> getLibrary(Authentication authentication, @RequestBody LibraryGetReq libraryInfo){
		try {
			LBUserDetails userDetails = (LBUserDetails) authentication.getDetails();
			User user = userDetails.getUser();
			
			// 도서관 Response
			List<LibraryGetRes> libraryList = libraryService.getLibraryList(libraryInfo);
			return ResponseEntity.status(200).body(libraryList);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(new UserInfoGetRes(400, "만료된 토큰입니다."));
		}
		
	}
}
