package com.pjt2.lb.service;

import java.util.List;
import com.pjt2.lb.entity.Book;

public interface LikeService {
	void addLike(String userEmail, String bookIsbn);
	void deleteLike(String userEmail, String bookIsbn);
	List<Book> getLikeBookList(String userEmail);
}
