package com.pjt2.lb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Book")
public class Book {

	@Id
	@Column(name="book_isbn")
	String bookIsbn;

	@Column(name="book_title")
	String bookTitle;
	
	@Column(name="book_author")
	String bookAuthor;
	
	@Column(name="book_pub")
	String bookPub;
	
	@Column(name="book_pub_date")
	String bookPubDate;
	
	@Column(name="book_price")
	int bookPrice;
	
	@Column(name="book_img_url")
	String bookImgUrl;
	
	@Column(name="book_desc")
	String bookDesc;
	
	@Column(name="book_category_code")
	String bookCategoryCode;
	
	@Column(name="book_like_cnt")
	int bookLikeCnt;
	
	@Column(name="book_keyword1")
	String bookKeyword1;
	
	@Column(name="book_keyword2")
	String bookKeyword2;
	
	@Column(name="book_keyword3")
	String bookKeyword3;
	
	
	// book-review
	@JsonManagedReference
	@OneToMany(mappedBy="book")
	List<Review> review = new ArrayList<Review>();

	// book-bookGrade
	@JsonManagedReference
	@OneToMany(mappedBy="book")
	List<BookGrade> bookGrade = new ArrayList<BookGrade>();
	
	// book-like
	@JsonManagedReference
	@OneToMany(mappedBy="book")
	List<BookLike> like = new ArrayList<BookLike>();

	// book-searchLog
	@JsonManagedReference
	@OneToMany(mappedBy="book")
	List<SearchLog> searchLog = new ArrayList<SearchLog>();
	
	// book-library
	@ManyToMany
	@JoinTable(
			name = "Library_Book",
			joinColumns = @JoinColumn(name = "book_isbn"), 
		    inverseJoinColumns = @JoinColumn(name = "lib_code"))
	List<Library> library = new ArrayList<Library>();
	
	// book-userBasedCFModel
	@JsonManagedReference
	@OneToMany(mappedBy="book")
	List<UserBasedCFModel> userBasedCF = new ArrayList<UserBasedCFModel>();
	
	// book-userPredictedGradeModel
	@JsonManagedReference
	@OneToMany(mappedBy="book")
	List<UserPredictedGradeModel> userPredictedGrade = new ArrayList<UserPredictedGradeModel>();

	// book-itemBasedCFModel
	@JsonManagedReference
	@OneToMany(mappedBy="book")
	List<ItemBasedCFModel> itemBasedCF = new ArrayList<ItemBasedCFModel>();
}
