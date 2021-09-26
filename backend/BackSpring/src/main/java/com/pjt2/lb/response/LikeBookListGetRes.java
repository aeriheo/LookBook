package com.pjt2.lb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeBookListGetRes {
	String BookIsbn;
	String BookTitle;
	String BookImgUrl;
}
