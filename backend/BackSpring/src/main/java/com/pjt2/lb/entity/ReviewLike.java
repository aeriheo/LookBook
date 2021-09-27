package com.pjt2.lb.entity;

import java.util.Date;
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
@Table(name="Review_Like")
public class ReviewLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="review_like_id")
	int reviewLikeId;
	
//	@Column(name="review_id")
//	int reviewId;
//	
//	@Column(name="user_email")
//	String userEmail;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="review_like_date")
	Date reviewLikeDate;
	
	// review-reviewLike
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="review_id")
	Review review;
	
	// user-reviewLike
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_email")
	User user;
}
