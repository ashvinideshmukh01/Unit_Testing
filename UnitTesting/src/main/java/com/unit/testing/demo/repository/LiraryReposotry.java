package com.unit.testing.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.unit.testing.demo.controller.Library;

@Repository
@EnableJpaRepositories
public interface LiraryReposotry extends JpaRepository<Library, String>{
	List<Library> findByAuthor(String author);
}
