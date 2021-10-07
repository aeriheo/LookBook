package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.BookLike;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.BookLikeRepository;
import com.pjt2.lb.repository.BookLikeRepositorySupport;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.response.BookListInfoRes;

@Service("BookLikeService")
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
		Book book = bookRepository.findByBookIsbn(bookIsbn);
		System.out.println(book.getBookLikeCnt());
		book.setBookLikeCnt(book.getBookLikeCnt()+1);
		System.out.println(book.getBookLikeCnt());
		// bookRepository.save(book);
		
		BookLike bookLike = new BookLike();
		bookLike.setBook(book);
		bookLike.setUser(user);
		return bookLikeRepository.save(bookLike);
	}

	@Override
	@Transactional 
	public int deleteLike(User user, String bookIsbn) {
		Book book = bookRepository.findByBookIsbn(bookIsbn);
		book.setBookLikeCnt(book.getBookLikeCnt()-1);
		// bookRepository.save(book);
		
		String userEmail = user.getUserEmail();
		int ans = bookLikeRepository.deleteByBookBookIsbnAndUserUserEmail(bookIsbn, userEmail);
		return ans;
	}
	
	@Override
	public List<BookListInfoRes> getLikeBookList(User user) {
		String userEmail = user.getUserEmail();
		return bookLikeRepositorySupport.getLikeBookList(userEmail);
	}
	
}
