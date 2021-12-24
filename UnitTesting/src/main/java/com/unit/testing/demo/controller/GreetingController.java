package com.unit.testing.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unit.testing.demo.model.GreetingResponse;

@RestController
public class GreetingController {
	
	@Autowired
	GreetingResponse greetingResponse;
	
	AtomicLong counter = new AtomicLong();
	
	@GetMapping("/greetings")
	public GreetingResponse greeting(@RequestParam(value = "name")String name) {
		greetingResponse.setContent("Welcome "+name);
		greetingResponse.setId(counter.incrementAndGet());
		return greetingResponse;
	}

}
