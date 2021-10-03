package com.pjt2.lb.service;

import org.springframework.stereotype.Service;

@Service("DjangoUrlService")
public class DjangoUrlserviceImpl implements DjangoUrlService {

	@Override
	public String getRecommUrl() {
		// 로컬 테스트 용
//		String url = "http://localhost:8000/recomm/";
		// 서버 배포 용
		String url = "https://j5a502.p.ssafy.io/recomm/";
		return url;
	}

}
