package com.pjt2.lb.service;

import org.springframework.stereotype.Service;

@Service("TestService")
public class TestServiceImpl implements TestService {

	@Override
	public String getDjangoUrl() {
		// EC2 서버 용
//		String url = "https://j5a502.p.ssafy.io/test/";
		// Local 테스트 용
		String url = "http://localhost:8000/test";
		return url;
	}

}
