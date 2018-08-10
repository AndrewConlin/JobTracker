package com.skilldistillery.jobtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{
	List<Note> findByJobId(int id);
}
