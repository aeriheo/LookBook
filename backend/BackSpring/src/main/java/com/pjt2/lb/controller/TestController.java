package com.pjt2.lb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pjt2.lb.service.TestService;

@CrossOrigin(
        origins = {"http://localhost:3000", "https://j5a502.p.ssafy.io/"},
        allowCredentials = "true", 
        allowedHeaders = "*", 
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS}
)
@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@GetMapping("/hello")
	public String hello() {
		String message = "Hello My name is Spring";
		return message;
	}
	
	@GetMapping("/django")
	public ResponseEntity<String> django() {
		String url = testService.getDjangoUrl();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// System.out.println("response : "+response);
		// System.out.println("response.getBody() : " + response.getBody());
		// System.out.println(response.getClass().getName());
		return response;
	}
	
	@GetMapping("/users")
	public ResponseEntity<Object[]> getUserInfo() {
		String url = testService.getUsersUrl();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);
		
		// System.out.println("response : " + response);
		// System.out.println("response.getBody() : " + response.getBody());
		// System.out.println(response.getClass().getName());
		return response;
	}
	
	@GetMapping("/recomm")
	public ResponseEntity<Object[]> postUserBasedCF(Authentication authentication){
		//
		return null;
	}
	
}
