package com.pjt2.lb.entity;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Book_Grade")
public class BookGrade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_grade_id")
	int bookGradeId;
	
//	@Column(name="book_isbn")
//	String bookIsbn;
//	
//	@Column(name="user_email")
//	String userEmail;
	
	@Column(name="book_grade")
	int bookGrade;
	
	
	// book-bookGrade
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="book_isbn")
	Book book;
	
	// user-bookGrade
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_email")
	User user;
}
