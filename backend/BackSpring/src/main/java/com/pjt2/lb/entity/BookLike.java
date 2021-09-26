package com.pjt2.lb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Book_Like")
public class BookLike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="like_id")
	int likeId;
	
//	@Column(name="book_isbn")
//	String bookIsbn;
//	
//	@Column(name="user_email")
//	String userEmail;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="like_date")
	Date likeDate;
	
	// search_log - user
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name ="user_email")
	User user;
		
	// search_log - book
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name ="book_isbn")
	Book book;
}
