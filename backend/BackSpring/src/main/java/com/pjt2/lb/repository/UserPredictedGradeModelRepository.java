package com.pjt2.lb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.UserPredictedGradeModel;

@Repository
public interface UserPredictedGradeModelRepository extends JpaRepository<UserPredictedGradeModel, String>{
}
