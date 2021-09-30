package com.pjt2.lb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.dao.LibraryDao;
import com.pjt2.lb.request.LibraryGetReq;
import com.pjt2.lb.response.LibraryGetRes;

@Service("LibraryService")
public class LibraryServiceImpl implements LibraryService {
	
	@Autowired
	LibraryDao libraryDao;

	@Override
	public List<LibraryGetRes> getLibraryList(String bookIsbn, String libGugun) {
		
		List<LibraryGetRes> libraryListGetRes = new ArrayList<LibraryGetRes>();
		
		libraryListGetRes = libraryDao.getLibraryList(bookIsbn, libGugun);
		
		return libraryListGetRes;
	}
	
}
