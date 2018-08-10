package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entites.JobStatus;

public interface JobStatusService {
	List<JobStatus> findAll();
}
