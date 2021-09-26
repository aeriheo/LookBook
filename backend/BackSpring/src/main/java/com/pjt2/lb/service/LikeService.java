package com.pjt2.lb.service;

import java.util.List;
import com.pjt2.lb.entity.Like;
import com.pjt2.lb.entity.User;
import com.pjt2.lb.response.LikeBookListGetRes;

public interface LikeService {
	Like addLike(User user, String bookIsbn);
	int deleteLike(User user, String bookIsbn);
	List<LikeBookListGetRes> getLikeBookList(User user);
}
