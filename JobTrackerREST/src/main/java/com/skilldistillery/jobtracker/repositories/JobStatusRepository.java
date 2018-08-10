package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.JobStatus;

public interface JobStatusRepository extends JpaRepository<JobStatus, Integer>{

}
