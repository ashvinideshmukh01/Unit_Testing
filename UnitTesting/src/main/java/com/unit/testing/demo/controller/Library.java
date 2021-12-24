package com.unit.testing.demo.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="storage2")
public class Library {
	@Column
	private String book_name;
	@Column
	@Id
	private String id;
	@Column
	private String isbn;
	@Column
	private int aisle;
	@Column
	private String author;
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getAisle() {
		return aisle;
	}
	public void setAisle(int aisle) {
		this.aisle = aisle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Library [book_name=" + book_name + ", id=" + id + ", isbn=" + isbn + ", aisle=" + aisle + ", author="
				+ author + "]";
	}
	
	
	
	
}
