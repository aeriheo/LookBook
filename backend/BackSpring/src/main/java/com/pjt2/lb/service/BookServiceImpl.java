package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.Book;
import com.pjt2.lb.repository.BookGradeRepositorySupport;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.BookRepositorySupport;
import com.pjt2.lb.repository.ReviewRepository;
import com.pjt2.lb.response.BookInfoGetRes;
import com.pjt2.lb.response.BookListInfoRes;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	BookGradeRepositorySupport bookGradeRepositorySupport;
	
	@Autowired
	BookRepositorySupport bookRepositorySupport;
	
	@Override
	public BookInfoGetRes getBookInfo(String bookIsbn, String userEmail) {
		// 책 기본 정보 가져오기
		BookInfoGetRes bookInfo = new BookInfoGetRes();
		Book book = bookRepository.findByBookIsbn(bookIsbn);
		BeanUtils.copyProperties(book, bookInfo);
		
		// isbn으로 리뷰 리스트 가져오기 리스트 가져오기
		// 리뷰 작성자, 리뷰내용, 리뷰 날짜, 리뷰-좋아요 개수
		// +내가 이 리뷰를 좋아요를 눌렀냐?

		
		// isbn으로 평균 평점 가져오기
		
		// 내 평점 가져오기
		try {
			int myGrade = bookGradeRepositorySupport.getBookGrade(bookIsbn, userEmail);
			bookInfo.setMyGrade(myGrade);
		} catch(NullPointerException e) {
			bookInfo.setMyGrade(0);
		}	
		
		// isLiked 내 좋아요 여부 가져오기
		
		
		return bookInfo;
	}

	@Override
	public List<BookListInfoRes> getSearchBookInfo(String searchKey, String searchWord) {
		return bookRepositorySupport.getSearchBookInfo(searchKey, searchWord);
	}

}
