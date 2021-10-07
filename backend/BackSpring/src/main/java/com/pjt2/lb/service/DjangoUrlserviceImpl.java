package com.pjt2.lb.service;

import org.springframework.stereotype.Service;

@Service("DjangoUrlService")
public class DjangoUrlserviceImpl implements DjangoUrlService {

	@Override
	public String getRecommUrl() {
		String url = "https://j5a502.p.ssafy.io/recomm/";
		return url;
	}

}
