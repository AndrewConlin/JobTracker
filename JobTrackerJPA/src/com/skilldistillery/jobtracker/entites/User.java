package com.skilldistillery.jobtracker.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String email;
	private String password;
	private String role;
	private boolean enabled;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Board> jobBoards;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Todo> todos;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<FileLocation> files;
	
	// gets and sets
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<Board> getJobBoards() {
		return jobBoards;
	}
	public void setJobBoards(List<Board> jobBoards) {
		this.jobBoards = jobBoards;
	}
	
	public List<Todo> getTodos() {
		return todos;
	}
	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
		
	public List<FileLocation> getFiles() {
		return files;
	}
	public void setFiles(List<FileLocation> files) {
		this.files = files;
	}
	
	//helpers

	public void addBoard(Board board) {
		if(jobBoards == null) jobBoards = new ArrayList<>();
		
		if(!jobBoards.contains(board)) {
			jobBoards.add(board);
			if(board.getUser()!= null) {
				board.getUser().getJobBoards().remove(board);
			}
			board.setUser(this);
		}
	}
	
	public void removeBoard(Board board) {
		board.setUser(null);
		if(jobBoards != null) {
			jobBoards.remove(board);
		}
	}
	
	public void addTodo(Todo todo) {
		if(todos == null) todos = new ArrayList<>();
		
		if(!todos.contains(todo)) {
			todos.add(todo);
			if(todo.getUser()!= null) {
				todo.getUser().getTodos().remove(todo);
			}
			todo.setUser(this);
		}
	}
	
	public void removeTodo(Todo todo) {
		todo.setUser(null);
		if(todos != null) {
			todos.remove(todo);
		}
	}
	
	public void addFile(FileLocation file) {
		if(files == null) files = new ArrayList<>();
		
		if(!files.contains(file)) {
			files.add(file);
			if(file.getUser()!= null) {
				file.getUser().getFiles().remove(file);
			}
			file.setUser(this);
		}
	}
	
	public void removeFile(FileLocation file) {
		file.setUser(null);
		if(files != null) {
			files.remove(file);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append(", email=").append(email)
				.append(", password=").append(password).append(", role=").append(role).append(", enabled=")
				.append(enabled).append("]");
		return builder.toString();
	}
	

}
