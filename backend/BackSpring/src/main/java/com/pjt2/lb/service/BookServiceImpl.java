package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.dao.BookDao;
import com.pjt2.lb.dao.ReviewDao;
import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.BookLike;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.repository.BookGradeRepositorySupport;
import com.pjt2.lb.repository.BookLikeRepository;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.BookRepositorySupport;
import com.pjt2.lb.repository.ReviewRepository;
import com.pjt2.lb.response.BookInfoGetRes;
import com.pjt2.lb.response.BookListInfoRes;
import com.pjt2.lb.response.BookReviewListInfoRes;

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
	
	@Autowired
	BookLikeRepository bookLikeRepository;
	
	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	BookDao bookDao;
	
	@Override
	public BookInfoGetRes getBookInfo(String bookIsbn, User user) {
		
		String userEmail = user.getUserEmail();
		
		// 책 기본 정보
		BookInfoGetRes bookInfo = new BookInfoGetRes();
		Book book = bookRepository.findByBookIsbn(bookIsbn);
		BeanUtils.copyProperties(book, bookInfo);
		
		// 최신 순 책 리뷰 리스트
		List<BookReviewListInfoRes> reviewRecentList = reviewDao.getBookRecentReviewList(bookIsbn, userEmail);
		bookInfo.setRecentReviewList(reviewRecentList);
		
		List<BookReviewListInfoRes> reviewRecommList = reviewDao.getBookRecommReviewList(bookIsbn, userEmail);
		bookInfo.setRecommReviewList(reviewRecommList);
		
		// 책 평균 평점
		Double avgGrade = bookGradeRepositorySupport.getBookGradeAvg(bookIsbn);
		bookInfo.setAvgGrade(avgGrade);
		
		// 나의 평점
		try {
			int myGrade = bookGradeRepositorySupport.getBookGrade(bookIsbn, userEmail);
			bookInfo.setMyGrade(myGrade);
		} catch(NullPointerException e) {
			bookInfo.setMyGrade(0);
		}	
		
		// 나의 좋아요 여부
		BookLike bookLike = bookLikeRepository.findByBookBookIsbnAndUserUserEmail(bookIsbn,userEmail);
		if(bookLike != null) bookInfo.setIsLiked(1);
		else bookInfo.setIsLiked(0);	
		
		return bookInfo;
	}

	@Override
	public List<BookListInfoRes> getSearchBookInfo(String searchKey, String searchWord) {
		return bookRepositorySupport.getSearchBookInfo(searchKey, searchWord);
	}

	@Override
	public List<BookListInfoRes> getCategoryList(int categoryId, String userEmail) {
		return bookDao.getCategoryList(categoryId, userEmail);
	}

}
