package com.pjt2.lb.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByUserEmail(String userEmail);
	User findUserByUserNickname(String userNickname);
	User findUserByRefreshToken(String refreshToken);
	@Transactional
	int deleteUserByUserEmail(String userEmail);
}
