package com.skilldistillery.jobtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.JobStatus;
import com.skilldistillery.jobtracker.repositories.JobStatusRepository;

@Service
public class JobStatusServiceImpl implements JobStatusService {

	@Autowired
	private JobStatusRepository jsRepo;
	
	@Override
	public List<JobStatus> findAll() {
		return jsRepo.findAll();
	}

}
