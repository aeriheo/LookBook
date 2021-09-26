package com.pjt2.lb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.ReviewLike;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Integer>{
	void deleteByReviewReviewIdAndUserUserEmail(Integer reviewId, String userEmail);
}
