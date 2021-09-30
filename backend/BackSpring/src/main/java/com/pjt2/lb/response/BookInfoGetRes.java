package com.pjt2.lb.response;

import java.util.List;

import lombok.Data;

@Data
public class BookInfoGetRes {
	private String bookIsbn;
	private String bookTitle;
	private String bookAuthor;
	private String bookPub;
	private String bookPubDate;
	private String bookImgUrl;
	private String bookDesc;
	private List<BookReviewListInfoRes> reviewList;
	private double avgGrade;
	private int myGrade;
	private int isLiked;
}
