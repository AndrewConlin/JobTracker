package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entites.Job;

public interface JobService {
	List<Job> findAllJobsByBoardId(int bid);
	Job findById(int jid);
	Job create(Job job, int bid);
	Job update(Job job, int jid);	
	Boolean destroyJob(int jid, int bid);
}
