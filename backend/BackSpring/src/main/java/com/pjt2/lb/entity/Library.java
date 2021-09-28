package com.pjt2.lb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Library")
public class Library {
	
	@Id
	@Column(name="lib_code")
	String libCode;
	
	@Column(name="lib_name")
	String libName;
	
	@Column(name="lib_addr")
	String libAddr;
	
	@Column(name="lib_lat")
	String libLat;
	
	@Column(name="lib_long")
	String libLong;
	
	@Column(name="lib_sido")
	String libSido;
	
	@Column(name="lib_gugun")
	String libGugun;
	
	@Column(name="lib_tel")
	String libTel;
	
	@Column(name="lib_url")
	String libUrl;
	
	// library - library_book
	@JsonManagedReference
	@ManyToMany(mappedBy="library")
	List<Book> book = new ArrayList<Book>();


}
