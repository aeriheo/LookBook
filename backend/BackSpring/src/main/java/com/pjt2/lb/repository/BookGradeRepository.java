package com.pjt2.lb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.BookGrade;

@Repository
public interface BookGradeRepository extends JpaRepository<BookGrade, Integer>{
	BookGrade findByBookBookIsbnAndUserUserEmail(String bookIsbn, String userEmail);
}
