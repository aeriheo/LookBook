package com.pjt2.lb.service;

import org.springframework.stereotype.Service;

@Service("DjangoUrlService")
public class DjangoUrlserviceImpl implements DjangoUrlService {

	@Override
	public String getRecommUrl() {
		String url = "http://localhost:8000/recomm/";
		return url;
	}

}
