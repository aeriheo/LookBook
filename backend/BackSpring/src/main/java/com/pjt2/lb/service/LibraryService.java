package com.pjt2.lb.service;

import java.util.List;

import com.pjt2.lb.request.LibraryGetReq;
import com.pjt2.lb.response.LibraryGetRes;

public interface LibraryService {
	List<LibraryGetRes> getLibraryList(LibraryGetReq libraryInfo);
}
