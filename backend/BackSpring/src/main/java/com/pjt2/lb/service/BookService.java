package com.pjt2.lb.service;

import java.util.List;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.BookInfoGetRes;
import com.pjt2.lb.response.BookListInfoRes;

public interface BookService {

	BookInfoGetRes getBookInfo(String bookIsbn, User user);

	List<BookListInfoRes> getSearchBookInfo(String searchKey, String searchWord);

	List<BookListInfoRes> getCategoryList(int categoryId, String userEmail);

}
