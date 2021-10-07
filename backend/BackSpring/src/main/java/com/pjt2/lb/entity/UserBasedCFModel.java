package com.pjt2.lb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="UserBasedCFModel")
public class UserBasedCFModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;

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
