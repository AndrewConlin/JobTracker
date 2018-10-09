package com.skilldistillery.jobtracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class TestController {
	
	@Value("${test}")
	private String test;

	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping() {
		System.out.println(test);
		return "pong";
	}
}
