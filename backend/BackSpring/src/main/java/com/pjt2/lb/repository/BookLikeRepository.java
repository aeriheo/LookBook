package com.pjt2.lb.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pjt2.lb.entity.BookLike;

@Repository
public interface BookLikeRepository extends JpaRepository<BookLike, Integer>{
	int deleteByBookBookIsbnAndUserUserEmail(String bookIsbn, String userEmail);
	int findByBookBookIsbnAndUserUserEmail(String bookIsbn, String userEmail);
}
