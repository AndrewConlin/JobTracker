package com.skilldistillery.jobtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{
	List<Job> findByBoardId(int id);
}
