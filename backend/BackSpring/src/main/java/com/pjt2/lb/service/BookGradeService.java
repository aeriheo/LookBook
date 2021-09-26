package com.pjt2.lb.service;

import com.pjt2.lb.entity.User;
import com.pjt2.lb.request.BookGradePostReq;

public interface BookGradeService {

	int insertBookGrade(User user, BookGradePostReq bookGradePostReq);

	int updateBookGrade(User user, BookGradePostReq bookGradePostReq);

}
