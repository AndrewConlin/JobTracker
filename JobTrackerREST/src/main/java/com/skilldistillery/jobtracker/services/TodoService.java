package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entites.Todo;

public interface TodoService {
	List<Todo> getTodosForUser(String username);
	List<Todo> getTodosForJob(String username, int jid);
	Todo getOneTodoForUser(String username, int tid);
	Todo creaeTodo(String username, Todo todo);
	Todo updateTodo(Todo todo, int tid);	
	Boolean deleteTodo(String username, int tid);
}
