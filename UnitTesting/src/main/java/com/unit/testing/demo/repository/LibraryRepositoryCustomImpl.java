package com.unit.testing.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unit.testing.demo.controller.Library;

public class LibraryRepositoryCustomImpl implements LibraryRepositoryCustom{

	@Autowired
	LiraryReposotry liraryReposotry;
	
	@Override
	public List<Library> findByAuthor(String author) {
		ArrayList<Library> authList = new ArrayList<>();
		List<Library> books = liraryReposotry.findAll();
		for (Library book : books) {
			if(book.getAuthor().equalsIgnoreCase(author)) {
				authList.add(book);
			}
		}
		return authList;
	}

}
