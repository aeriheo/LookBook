package com.pjt2.lb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.BookGrade;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookGradeRepository;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.UserRepository;


@Service
public class BookGradeServiceImpl implements BookGradeService {
	
	private static final int SUCCESS = 1;
    private static final int FAIL = -1;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    BookGradeRepository bookGradeRepository;


	@Override
	public int insertBookGrade(String userEmail, String bookIsbn, int grade) {
		try {
			BookGrade bookGrade = new BookGrade();
			bookGrade.setBookGrade(grade);
			User user = userRepository.findUserByUserEmail(userEmail);
			bookGrade.setUser(user);
			Book book = bookRepository.findByBookIsbn(bookIsbn);
			bookGrade.setBook(book);
			
			bookGradeRepository.save(bookGrade);
			return SUCCESS;
			
		} catch (Exception e) {
			return FAIL;
		}
	}


	@Override
	public int updateBookGrade(String userEmail, String bookIsbn, int grade) {
		try {
			BookGrade bookGrade = bookGradeRepository.findByBookBookIsbnAndUserUserEmail(bookIsbn, userEmail);
			bookGrade.setBookGrade(grade);
			bookGradeRepository.save(bookGrade);
			return SUCCESS;
			
		} catch (Exception e) {
			return FAIL;
		}
	}



}
