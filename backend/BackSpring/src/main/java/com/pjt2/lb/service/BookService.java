package com.pjt2.lb.service;

import com.pjt2.lb.response.BookInfoGetRes;

public interface BookService {

	BookInfoGetRes getBookInfo(String bookIsbn, String userEmail);

}
