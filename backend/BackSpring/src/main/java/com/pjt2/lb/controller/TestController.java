package com.pjt2.lb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("v1/test")
public class TestController {
	
	@GetMapping("/hello")
	public String hello() {
		String message = "Hello My name is Spring";
		return message;
	}
	
	@GetMapping("/django")
	public ResponseEntity<String> django() {
		String url = "http://localhost:8000/test/";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// System.out.println("response : "+response);
		// System.out.println("response.getBody() : " + response.getBody());
		// System.out.println(response.getClass().getName());
		return response;
	}
	
	@GetMapping("/users")
	public ResponseEntity<Object[]> getUserInfo() {
		String url = "http://localhost:8000/test/user";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);
		
		// System.out.println("response : " + response);
		// System.out.println("response.getBody() : " + response.getBody());
		// System.out.println(response.getClass().getName());
		return response;
	}

//	@GetMapping("/user")
//	public ResponseEntity<BaseResponseBody> getUserInfo() {
//		String url = "http://localhost:8000/test/user";
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);
//		
//		System.out.println("response : " + response);
//		System.out.println("response.getBody() : " + response.getBody());
//		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "유저 목록 조회 성공"));
//	}
	
}
