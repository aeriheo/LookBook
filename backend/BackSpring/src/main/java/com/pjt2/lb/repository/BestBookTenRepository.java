package com.pjt2.lb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.BestBookTen;

@Repository
public interface BestBookTenRepository extends JpaRepository<BestBookTen, Integer>{
	List<BestBookTen> findTop10ByOrderByIdDesc();
}
