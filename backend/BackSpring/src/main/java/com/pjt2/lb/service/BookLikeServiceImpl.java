package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt2.lb.entity.BookLike;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.BookLikeRepository;
import com.pjt2.lb.repository.BookLikeRepositorySupport;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.response.BookListInfoRes;

@Service("BookLikeService")
//@Service
public class BookLikeServiceImpl implements BookLikeService{
	
	@Autowired
	BookLikeRepository bookLikeRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookLikeRepositorySupport bookLikeRepositorySupport;
	
	@Override
	public BookLike addLike(User user, String bookIsbn) {
		BookLike bookLike = new BookLike();
		
		// Book 조회해서 넣기
		bookLike.setBook(bookRepository.findByBookIsbn(bookIsbn));
		// User 조회해서 넣기
		bookLike.setUser(user);
		
		return bookLikeRepository.save(bookLike);
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
		int ans = bookLikeRepository.deleteByBookBookIsbnAndUserUserEmail(bookIsbn, userEmail);
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
		return bookLikeRepositorySupport.getLikeBookList(userEmail);
	}

	// getLikeBookList 테스트
	/*
	@Override
	public List<BookListInfoRes> getLikeBookList(String userEmail) {
		// 이메일에 해당하는 모든 책 정보 가져오기
		return likeRepositorySupport.getLikeBookList(userEmail);
	}
	*/
	
}
