package com.unit.testing.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.unit.testing.demo.model.addRespose;
import com.unit.testing.demo.repository.LiraryReposotry;
import com.unit.testing.demo.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LiraryReposotry LiraryReposotry;
	@Autowired
	addRespose addRespose;
	@Autowired
	LibraryService libraryService;
	
	private static final Logger logger =LoggerFactory.getLogger(LibraryController.class);
	
	@PostMapping("/addbooks")
	public ResponseEntity<com.unit.testing.demo.model.addRespose> addBook(@RequestBody Library lirary) {
		String id= libraryService.buildId(lirary.getIsbn(),lirary.getAisle());
		boolean checkBookAlreadyExist = libraryService.checkBookAlreadyExist(id);
		if(!checkBookAlreadyExist) {
		logger.info("Book already does not exist so creating new one!!!");
		lirary.setId(lirary.getIsbn()+lirary.getAisle());
		LiraryReposotry.save(lirary);
		addRespose.setMsg("book Added!!!");
		addRespose.setId(lirary.getIsbn()+lirary.getAisle());
		return new ResponseEntity<addRespose>(addRespose, HttpStatus.CREATED);
		
		}else {
			logger.info("Book already  exist so not creating new one!!!");
			addRespose.setMsg("Book already exist!!!");
			addRespose.setId(lirary.getIsbn()+lirary.getAisle());
		return new ResponseEntity<addRespose>(addRespose, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/getooks/{id}")
	public Library getBookById(@PathVariable(value = "id") String id) {
		try {
		Library byId = ( LiraryReposotry.findById(id)).get();
		logger.info("Book details are returned!!!");
		return byId;
		}catch(Exception e) {
			logger.info("Book details are not available!!!");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/getooks/author")
	public List<Library> getBookByAuthor(@PathParam(value = "author") String author) {
		return LiraryReposotry.findByAuthor(author);
		
	}
	
	@PutMapping("/updatebook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value = "id")String id,@RequestBody Library library) {
		Library existingBook = LiraryReposotry.findById(id).get();
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		LiraryReposotry.save(library);
		return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebook")
	public ResponseEntity deleteBook(@RequestBody Library library) {
		Library delId = LiraryReposotry.findById(library.getId()).get();
		LiraryReposotry.delete(delId);
		return new ResponseEntity("Book Deleted!!!",HttpStatus.CREATED);
	}
}
