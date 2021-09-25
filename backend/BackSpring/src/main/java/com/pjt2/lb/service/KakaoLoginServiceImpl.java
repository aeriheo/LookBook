package com.pjt2.lb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjt2.lb.request.KakaoOAuthToken;
import com.pjt2.lb.request.KakaoProfile;

@Service
public class KakaoLoginServiceImpl implements KakaoLoginService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	private final String kakaoOauth2ClinetId = "a765ac439be73b3505f709a713a0dcd0";
	private final String frontendRedirectUrl = "http://localhost:3000";

	@Override
	public KakaoOAuthToken getKakaoTokenApi(String code) {

		KakaoOAuthToken kakaoOAuthToken = null;
		
		// POST 방식으로 token에 대한 key=value 데이터 요청
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoOauth2ClinetId);
		params.add("redirect_uri", frontendRedirectUrl + "/callback/kakao");
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		String url = "https://kauth.kakao.com/oauth/token";

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, kakaoTokenRequest, String.class);

		try {
			kakaoOAuthToken = objectMapper.readValue(response.getBody(), KakaoOAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return kakaoOAuthToken;
	}

	@Override
	public KakaoProfile getUserByAccessToken(String accessToken) {
		
		KakaoProfile kakaoProfile = null;
		
		// HttpHeader 객체 생성
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// HttpHeader와 HttpBody를 하나의 객체에 담는다.
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(params, headers);

		String url = "https://kapi.kakao.com/v2/user/me";

		// POST 방식으로 Http 요청
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, kakaoProfileRequest, String.class);
//		System.out.println(response);
		try {
			kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return kakaoProfile;


	}

}
