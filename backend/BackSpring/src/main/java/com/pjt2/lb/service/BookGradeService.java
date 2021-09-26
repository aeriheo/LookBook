package com.pjt2.lb.service;

import com.pjt2.lb.request.BookGradePostReq;

public interface BookGradeService {

	int insertBookGrade(String userEmail, BookGradePostReq bookGradePostReq);

	int updateBookGrade(String userEmail, BookGradePostReq bookGradePostReq);

}
