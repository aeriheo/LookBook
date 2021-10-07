package com.pjt2.lb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pjt2.lb.response.BookListInfoRes;

@Mapper
public interface BookDao {

	List<BookListInfoRes> getCategoryList(@Param("categoryId") int categoryId, @Param("userEmail") String userEmail);

}
