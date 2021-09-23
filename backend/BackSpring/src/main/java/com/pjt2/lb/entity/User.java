package com.pjt2.lb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="User")
public class User {
	
	@Id
	@Column(name="user_email")
	String userEmail;
	
	@Column(name="user_password")
	String userPassword;
	
	@Column(name="user_name")
	String userName;
	
	@Column(name="user_nickname")
	String userNickname;
	
	@Column(name="user_profile_url")
	String userProfileUrl;
	
	@Column(name="user_join_type")
	int userJoinType;
	
	@Column(name="access_token")
	String accessToken;
	

	// user-review
	@JsonManagedReference
	@OneToMany(mappedBy="user")
	List<Review> review = new ArrayList<Review>();
	
	// user-reviewLike
	@JsonManagedReference
	@OneToMany(mappedBy="user")
	List<ReviewLike> reviewLike = new ArrayList<ReviewLike>();

	// user-bookGrade
	@JsonManagedReference
	@OneToMany(mappedBy="user")
	List<BookGrade> bookGrade = new ArrayList<BookGrade>();
	
	// user-like
	@JsonManagedReference
	@OneToMany(mappedBy="user")
	List<Like> like = new ArrayList<Like>();

	// user-searchLog
	@JsonManagedReference
	@OneToMany(mappedBy="user")
	List<SearchLog> searchLog = new ArrayList<SearchLog>();
}
