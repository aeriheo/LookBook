package com.pjt2.lb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Book;

@Service("LikeService")
public class LikeServiceImpl {
	
	@Override
	public void addLike(String userEmail, String bookIsbn) {
		// 이메일+isbn으로 추가
		
	}
	
	@Override
	public void deleteLike(String userEmail, String bookIsbn) {
		// 이메일+isbn으로 삭제
	}
	
	@Override
	public List<Book> getLikeBookList(String userEmail) {
		// 이메일에 해당하는 모든 책 정보 가져오기
	}
}
