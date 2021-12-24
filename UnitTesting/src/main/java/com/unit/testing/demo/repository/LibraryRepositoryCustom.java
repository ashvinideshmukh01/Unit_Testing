package com.unit.testing.demo.repository;

import java.util.List;

import com.unit.testing.demo.controller.Library;

public interface LibraryRepositoryCustom {
	List<Library> findByAuthor(String author);
}
