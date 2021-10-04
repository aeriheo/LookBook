package com.pjt2.lb.service;

import java.util.List;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.BookGradePostReq;
import com.pjt2.lb.response.BookGradeListInfoRes;

public interface BookGradeService {

	int insertBookGrade(User user, BookGradePostReq bookGradePostReq);

	int updateBookGrade(User user, BookGradePostReq bookGradePostReq);

	List<BookGradeListInfoRes> getBookGradeList(String userEmail);

}
