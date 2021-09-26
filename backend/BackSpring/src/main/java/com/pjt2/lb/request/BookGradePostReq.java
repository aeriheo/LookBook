package com.pjt2.lb.request;

import lombok.Data;

@Data
public class BookGradePostReq {
	private String bookIsbn;
	private int bookGrade;
}
