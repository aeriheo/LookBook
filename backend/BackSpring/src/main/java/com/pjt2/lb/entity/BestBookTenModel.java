package com.pjt2.lb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="BestBookTenModel")
public class BestBookTenModel {

	@Id
	@Column(name="id")
	int id;
	
	// book-bookGrade
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="book_isbn")
	Book book;
}
