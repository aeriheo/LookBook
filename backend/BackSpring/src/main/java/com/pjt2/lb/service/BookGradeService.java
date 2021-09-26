package com.pjt2.lb.service;

public interface BookGradeService {

	int insertBookGrade(String userEmail, String bookIsbn, int grade);

	int updateBookGrade(String userEmail, String bookIsbn, int grade);

}
