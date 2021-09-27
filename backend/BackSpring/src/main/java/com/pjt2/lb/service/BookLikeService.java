package com.pjt2.lb.service;

import java.util.List;

import com.pjt2.lb.entity.BookLike;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.BookListInfoRes;

public interface BookLikeService {
	BookLike addLike(User user, String bookIsbn);
//	BookLike addLike(String userEmail, String bookIsbn);

	int deleteLike(User user, String bookIsbn);
//	int deleteLike(String userEmail, String bookIsbn);
	
	List<BookListInfoRes> getLikeBookList(User user);
//	List<BookListInfoRes> getLikeBookList(String userEmail);
	
	int getLikeState(String userEmail, String bookIsbn);
}
