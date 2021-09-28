package com.pjt2.lb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pjt2.lb.response.LibraryGetRes;

@Mapper
public interface LibraryDao {
	public List<LibraryGetRes> getLibraryList(@Param("bookIsbn") String bookIsbn, @Param("libGugun") String libGugun);
}
