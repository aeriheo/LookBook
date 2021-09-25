package com.pjt2.lb.request;

import lombok.Data;

@Data
public class KakaoProfile {
	public Integer id;
	public KakaoAccount kakao_account;

	@Data
	public class KakaoAccount {
		public Boolean hasEmail;
		public Boolean emailNeedsAgreement;
		public Boolean isEmailValid;
		public Boolean isEmailVerified;
		public String email;

	}
}
