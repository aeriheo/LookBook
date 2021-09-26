package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt2.lb.entity.BookLike;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.LikeRepository;
import com.pjt2.lb.repository.LikeRepositorySupport;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.response.BookListInfoRes;

@Service("LikeService")
//@Service
public class LikeServiceImpl implements LikeService{
	
	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LikeRepositorySupport likeRepositorySupport;
	
	@Override
	public BookLike addLike(User user, String bookIsbn) {
		BookLike bookLike = new BookLike();
		
		// Book 조회해서 넣기
		bookLike.setBook(bookRepository.findByBookIsbn(bookIsbn));
		// User 조회해서 넣기
		bookLike.setUser(user);
		
		return likeRepository.save(bookLike);
	}

	// addLike 테스트
	/*
	@Override
	public BookLike addLike(String userEmail, String bookIsbn) {
		BookLike like = new BookLike();
		
		try{
			// Book 조회해서 넣기
			like.setBook(bookRepository.findByBookIsbn(bookIsbn));

			// User 조회해서 넣기
			User user = userRepository.findUserByUserEmail(userEmail);
			like.setUser(user);
			
			System.out.println(like.getUser());
			
			// 오류발생지
			likeRepository.save(like);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	*/

	
	@Override
	@Transactional 
	public int deleteLike(User user, String bookIsbn) {
		String userEmail = user.getUserEmail();
		int ans = likeRepository.deleteByBookBookIsbnAndUserUserEmail(bookIsbn, userEmail);
		return ans;
	}
	
	// deleteLike 테스트
	/*
	@Override
	@Transactional 
	public int deleteLike(String userEmail, String bookIsbn) {
		try {
			int ans = likeRepository.deleteByBookBookIsbnAndUserUserEmail(bookIsbn, userEmail);
			return ans;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	*/
	
	@Override
	public List<BookListInfoRes> getLikeBookList(User user) {
		
		String userEmail = user.getUserEmail();
		
		// 이메일에 해당하는 모든 책 정보 가져오기
		return likeRepositorySupport.getLikeBookList(userEmail);
	}

	// getLikeBookList 테스트
	/*
	@Override
	public List<BookListInfoRes> getLikeBookList(String userEmail) {
		// 이메일에 해당하는 모든 책 정보 가져오기
		return likeRepositorySupport.getLikeBookList(userEmail);
	}
	*/
	
	@Override
	public int getLikeState(String userEmail, String bookIsbn) {
		int ans = likeRepository.findByBookBookIsbnAndUserUserEmail(userEmail, bookIsbn);
		return ans;
	}
	
}
