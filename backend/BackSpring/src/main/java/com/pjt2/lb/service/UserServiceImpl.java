package com.pjt2.lb.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pjt2.lb.common.response.BaseResponseBody;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.request.UserInfoPutReq;
import com.pjt2.lb.request.UserProfilePostReq;
import com.pjt2.lb.request.UserRegisterPostReq;
import com.pjt2.lb.response.UserInfoGetRes;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public User registerUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		
		user.setUserEmail(userRegisterInfo.getUserEmail());
		user.setUserPassword(passwordEncoder.encode(userRegisterInfo.getUserPassword()));
		user.setUserName(userRegisterInfo.getUserName());
		user.setUserNickname(userRegisterInfo.getUserNickname());
		
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserEmail(String userEmail) {
		User user = userRepository.findUserByUserEmail(userEmail);
		return user;
	}

	@Override
	public User getUserByUserNickname(String userNickname) {
		User user = userRepository.findUserByUserNickname(userNickname);
		return user;
	}

	@Override
	public UserInfoGetRes getUserInfo(User user) {
		UserInfoGetRes userInfo = new UserInfoGetRes();
		BeanUtils.copyProperties(user, userInfo);
		return userInfo;
	}

	@Override
	public User getUserByRefreshToken(String refreshToken) {
		User user = userRepository.findUserByRefreshToken(refreshToken);
		return user;
	}

	@Override
	public int deleteUser(String userEmail) {
		return userRepository.deleteUserByUserEmail(userEmail);
	}

	@Override
	public int update(User user, UserInfoPutReq userUpdateInfo) {
		try {
			String userNickname = userUpdateInfo.getUserNickname();
			String userPassword = userUpdateInfo.getUserPassword();
			if(userRepository.findUserByUserNickname(userNickname) != null &&
					!user.getUserNickname().equals(userNickname)){
						return 2;
					}
			
			userUpdateInfo.setUserPassword(passwordEncoder.encode(userPassword));
			BeanUtils.copyProperties(userUpdateInfo, user);
			userRepository.save(user);
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int updateProfile(User user, UserProfilePostReq userProfileInfo) {
		try {
			String userProfileUrl = userProfileInfo.getUserProfileUrl();
			user.setUserProfileUrl(userProfileUrl);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
