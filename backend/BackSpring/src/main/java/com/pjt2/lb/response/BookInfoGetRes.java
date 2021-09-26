package com.pjt2.lb.response;

import java.util.List;

import com.pjt2.lb.entity.Review;

import lombok.Data;

@Data
public class BookInfoGetRes {
	private String bookIsbn;
	private String bookTitle;
	private String bookPub;
	private String bookPubDate;
	private String bookImgUrl;
	private String bookDesc;
	private List<Review> reiveiwList;
	private int meanGrade;
	private int myGrade;
	private int isLiked;
}
