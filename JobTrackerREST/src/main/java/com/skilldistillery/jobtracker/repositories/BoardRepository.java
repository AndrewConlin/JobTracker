package com.skilldistillery.jobtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	List<Board> findByUserUsername(String username);
	Board findByUserUsernameAndId(String username, int id);
	
}
