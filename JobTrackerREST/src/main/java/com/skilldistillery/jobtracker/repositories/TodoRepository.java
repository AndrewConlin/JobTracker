package com.skilldistillery.jobtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	List<Todo> findByUserUsername(String username);
	List<Todo> findByJobId(int id);
}
