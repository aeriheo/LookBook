package com.pjt2.lb.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

//import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer>{
	int deleteByBookBookIsbnAndUserUserEmail(String bookIsbn, String userEmail);
}
