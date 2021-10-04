package com.pjt2.lb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGradeListInfoRes {
	String BookIsbn;
	String BookTitle;
	String BookImgUrl;
	int bookGrade;
}
