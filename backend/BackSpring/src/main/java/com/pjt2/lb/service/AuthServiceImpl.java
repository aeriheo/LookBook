package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pjt2.lb.common.auth.JwtAuthenticationFilter;
import com.pjt2.lb.common.util.JwtTokenUtil;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.request.TokenPostReq;
import com.pjt2.lb.request.UserLoginPostReq;
import com.pjt2.lb.response.BookGradeListInfoRes;
import com.pjt2.lb.response.KakaoLoginRes;
import com.pjt2.lb.response.TokenPostRes;
import com.pjt2.lb.response.UserLoginPostRes;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserService userService;
	
	@Autowired
	BookGradeService bookGradeService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	public TokenPostRes reissue(TokenPostReq refreshToken) {

		String token = refreshToken.getRefreshToken();
		if (JwtTokenUtil.validateToken(token)) {
			User user = userService.getUserByRefreshToken(token);
			if (user == null) {
				return new TokenPostRes(400, "올바른 사용자가 아닙니다.", null, null);
			}
			String userEmail = user.getUserEmail();

			String newAccessToken = JwtTokenUtil.getToken(userEmail);
			String newRefreshToken = JwtTokenUtil.getRefreshToken();

			user.setRefreshToken(newRefreshToken);
			userRepository.save(user);

			TokenPostRes tokenPostRes = new TokenPostRes(200, "토큰 재발급", newAccessToken, newRefreshToken);
			return tokenPostRes;
		}
		TokenPostRes tokenPostRes = new TokenPostRes(400, "만료된 토큰. 재 로그인 필요", null, null);
		return tokenPostRes;
	}

	@Override
	public UserLoginPostRes login(UserLoginPostReq loginInfo) {
		String userEmail = loginInfo.getUserEmail();
		String userPassword = loginInfo.getUserPassword();

		try {

			User user = userService.getUserByUserEmail(userEmail);
			
			List<BookGradeListInfoRes> bookGradeList = bookGradeService.getBookGradeList(userEmail);

			int bookGradeListSize = bookGradeList.size();
			
			String accessToken = JwtTokenUtil.getToken(userEmail);
			String refreshToken = JwtTokenUtil.getRefreshToken();

			if (passwordEncoder.matches(userPassword, user.getUserPassword())) {
				user.setRefreshToken(refreshToken);
				userRepository.save(user);
				return new UserLoginPostRes(200, "로그인에 성공하였습니다.", accessToken, refreshToken, bookGradeListSize);
			} else {
				return new UserLoginPostRes(401, "잘못된 비밀번호 입니다.", null, null, 0);
			}
		} catch (NullPointerException e) {
			return new UserLoginPostRes(404, "존재하지 않는 계정입니다.", null, null, 0);
		}
	}

	@Override
	public KakaoLoginRes kakaologin(String userEmail) {
		User user = userService.getUserByUserEmail(userEmail);
		KakaoLoginRes kakaoLoginRes = new KakaoLoginRes();
		try {
			List<BookGradeListInfoRes> bookGradeList = bookGradeService.getBookGradeList(userEmail);
			int bookGradeListSize = bookGradeList.size();
			String accessToken = JwtTokenUtil.getToken(userEmail);
			String refreshToken = JwtTokenUtil.getRefreshToken();
			
			user.setRefreshToken(refreshToken);
			userRepository.save(user);
			
			kakaoLoginRes.setStatusCode(200);
			kakaoLoginRes.setMessage("로그인에 성공하였습니다.");
			kakaoLoginRes.setAccessToken(accessToken);
			kakaoLoginRes.setRefreshToken(refreshToken);
			kakaoLoginRes.setActionCode(false);
			kakaoLoginRes.setBookGradeListLength(bookGradeListSize);
			return kakaoLoginRes;

		} catch (Exception e) {
			kakaoLoginRes.setStatusCode(400);
			kakaoLoginRes.setMessage("로그인 오류");
			return kakaoLoginRes;
		}
	}

}
