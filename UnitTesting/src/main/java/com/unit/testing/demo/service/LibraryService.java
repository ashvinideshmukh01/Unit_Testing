package com.unit.testing.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unit.testing.demo.controller.Library;
import com.unit.testing.demo.repository.LiraryReposotry;

@Service
public class LibraryService {
	 
	@Autowired
	LiraryReposotry liraryReposotry;
	
	public String buildId(String isbn,int aisle) {
		if(isbn.startsWith("z")) {
			return "OLD"+isbn+aisle;
		}
		return isbn+aisle;
		
	}
	public boolean checkBookAlreadyExist(String id) {
		Optional<Library> fid = liraryReposotry.findById(id);
		if(fid.isEmpty()) {
			return false;
		}
		return true;
		
	}
}
