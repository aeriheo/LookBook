package com.pjt2.lb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="review_id")
	int reviewId;
	
//	@Column(name="book_isbn")
//	String bookIsbn;
//	
//	@Column(name="user_email")
//	String userEmail;
	
	@Column(name="review_content")
	String reviewContent;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="review_date")
	Date reviewDate;
	
	@Column(name="review_like_count")
	String reviewLikeCount;
	
	// review-reviewLike
	@JsonManagedReference
	@OneToMany(mappedBy="review")
	List<ReviewLike> reviewLike = new ArrayList<ReviewLike>();
	
	// user-review
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_email")
	User user;

	// book-review
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="book_isbn")
	Book book;
}
