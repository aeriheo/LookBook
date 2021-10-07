package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.BookGrade;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookGradeRepository;
import com.pjt2.lb.repository.BookGradeRepositorySupport;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.request.BookGradePostReq;
import com.pjt2.lb.response.BookGradeListInfoRes;


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
    
    @Autowired
    BookGradeRepositorySupport bookGradeRepositorySupport;


	@Override
	public int insertBookGrade(User user, BookGradePostReq bookGradePostReq) {
		
		String bookIsbn = bookGradePostReq.getBookIsbn();
		int grade = bookGradePostReq.getBookGrade();
		
		try {
			BookGrade bookGrade = new BookGrade();
			bookGrade.setBookGrade(grade);
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
	public int updateBookGrade(User user, BookGradePostReq bookGradePostReq) {
		
		String bookIsbn = bookGradePostReq.getBookIsbn();
		int grade = bookGradePostReq.getBookGrade();
		
		try {
			BookGrade bookGrade = bookGradeRepository.findByBookBookIsbnAndUserUserEmail(bookIsbn, user.getUserEmail());
			bookGrade.setBookGrade(grade);
			bookGradeRepository.save(bookGrade);
			return SUCCESS;
			
		} catch (Exception e) {
			return FAIL;
		}
	}


	@Override
	public List<BookGradeListInfoRes> getBookGradeList(String userEmail) {
		List<BookGradeListInfoRes> bookGradeListInfoRes = bookGradeRepositorySupport.getBookGradeList(userEmail);
		return bookGradeListInfoRes;
	}



}
