package com.pjt2.lb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{
	Book findByBookIsbn(String bookIsbn);
}
