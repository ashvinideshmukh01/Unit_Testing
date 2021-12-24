package com.unit.testing.demo.model;

import org.springframework.stereotype.Component;

@Component
public class GreetingResponse {
	
	private Long id;
	private String content;
	
	public Long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String name) {
		this.content = name;
	}
	
	
}
