package com.skilldistillery.jobtracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.Todo;
import com.skilldistillery.jobtracker.services.TodoService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	private String username= "andrew"; // test username, will be replaced by Spring Security Principal object 

	
	@RequestMapping(path="todos", method=RequestMethod.GET)
	public List<Todo> indexByUser(Principal principal){
		return todoService.getTodosForUser(principal.getName());
	}
	
	@RequestMapping(path="jobs/{jid}/todos", method=RequestMethod.GET)
	public List<Todo> indexByJob(@PathVariable int jid, Principal principal){
		return todoService.getTodosForJob(principal.getName(), jid);
	}
	
	@RequestMapping(path="todos/{tid}", method=RequestMethod.GET)
	public Todo show(@PathVariable int tid, Principal principal){
		return todoService.getOneTodoForUser(principal.getName(), tid);
	}
	
	@RequestMapping(path="todos", method=RequestMethod.POST)
	public Todo create(@RequestBody Todo todo, HttpServletResponse res, Principal principal) {
		todo = todoService.creaeTodo(principal.getName(), todo);
		
		if(todo == null) res.setStatus(500);
		else res.setStatus(201);
		
		return todo;
	}
	
	@RequestMapping(path="todos/{tid}", method=RequestMethod.PATCH)
	public Todo update(@RequestBody Todo todo, @PathVariable int tid, Principal principal) {
		return todoService.updateTodo(todo, tid);
	}
	
	
	@RequestMapping(path="todos/{tid}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int tid, Principal principal) {
		return todoService.deleteTodo(principal.getName(), tid);
	}
	
}
