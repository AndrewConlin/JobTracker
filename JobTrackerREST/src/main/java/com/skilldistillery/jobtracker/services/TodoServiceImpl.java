package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.Todo;
import com.skilldistillery.jobtracker.entites.User;
import com.skilldistillery.jobtracker.repositories.TodoRepository;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Todo> getTodosForUser(String username) {
		return todoRepo.findByUserUsername(username);
	}

	@Override
	public List<Todo> getTodosForJob(String username, int jid) {
		return todoRepo.findByJobId(jid);
	}

	@Override
	public Todo getOneTodoForUser(String username, int tid) {
		Optional<Todo> optTodo= todoRepo.findById(tid);
		
		if(optTodo.isPresent()) {
			Todo managed = optTodo.get();
			if(managed.getUser().getUsername().equals(username)) {
				return managed;
			}
		}
		return null;
	}

	@Override
	public Todo creaeTodo(String username, Todo todo) {
		User managed = userRepo.findByUsername(username);
		todo.setUser(managed);
		
		return todoRepo.saveAndFlush(todo);
	}

	@Override
	public Todo updateTodo(Todo todo, int tid) {
		todo.setId(tid);
		return todoRepo.saveAndFlush(todo);
	}

	@Override
	public Boolean deleteTodo(String username, int tid) {
		Optional<Todo> optTodo = todoRepo.findById(tid);
		if(optTodo.isPresent()) {
			Todo managed = optTodo.get();
			if(managed.getUser().getUsername().equals(username)) {
				todoRepo.delete(managed);
				return true;
			}
		}
		return null;
	}
	
	
}
