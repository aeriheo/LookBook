package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Like;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.LikeRepository;
import com.pjt2.lb.repository.LikeRepositorySupport;
import com.pjt2.lb.response.LikeBookListGetRes;

@Service("LikeService")
//@Service
public class LikeServiceImpl implements LikeService{
	
	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	LikeRepositorySupport likeRepositorySupport;
	
	@Override
	public Like addLike(User user, String bookIsbn) {
		// 이메일+isbn으로 추가
		Like like = new Like();
		
		// Book 조회해서 넣기
//		like.setBook();
		// User 조회해서 넣기
		like.setUser(user);
		
		return likeRepository.save(like);
	}
	
	@Override
	public int deleteLike(User user, String bookIsbn) {
		String userEmail = user.getUserEmail();
		int ans = likeRepository.deleteByBookBookIsbnAndUserUserEmail(bookIsbn, userEmail);
		return ans;
	}
	
	@Override
	public List<LikeBookListGetRes> getLikeBookList(User user) {
		
		String userEmail = user.getUserEmail();
		
		// 이메일에 해당하는 모든 책 정보 가져오기
		return likeRepositorySupport.getLikeBookList(userEmail);
	}
	
	// 가빈 ) 내 이메일과 책 정보로 좋아요를 했는지 확인이 필요하다.
	
}
