package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.Board;
import com.skilldistillery.jobtracker.entites.Job;
import com.skilldistillery.jobtracker.repositories.BoardRepository;
import com.skilldistillery.jobtracker.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepo;

	@Autowired
	private BoardRepository boardRepo;

	@Override
	public List<Job> findAllJobsByBoardId(int bid) {
		return jobRepo.findByBoardId(bid);
	}

	@Override
	public Job findById(int jid) {
		Optional<Job> optJob = jobRepo.findById(jid);
		if(optJob.isPresent()) {
			return optJob.get();
		}
		return null;
	}
	

	@Override
	public Job create(Job job, int bid) {
		Optional<Board> optBoard = boardRepo.findById(bid);
		if(optBoard.isPresent()) {
			Board managed = optBoard.get();
			job.setBoard(managed);
		}
		return jobRepo.saveAndFlush(job);
	}
	
	@Override
	public Job update(Job job, int jid) {
		Optional<Job> optJob = jobRepo.findById(jid);
		if(optJob.isPresent()) {
			Job managed = optJob.get();
			managed.setTitle(job.getTitle());
			managed.setSalary(job.getSalary());
			managed.setDescription(job.getDescription());
			managed.setApplicationDate(job.getApplicationDate());
			managed.setOfferDate(job.getOfferDate());
			managed.setStatus(job.getStatus());
			return jobRepo.saveAndFlush(managed);
		}
		return null;
	}

	@Override
	public Boolean destroyJob(int jid, int bid) {
		Optional<Job> optJob = jobRepo.findById(jid);
		if(optJob.isPresent()) {
			Job managed = optJob.get();
			if (managed.getBoard().getId() == bid) {
				jobRepo.delete(managed);
				return true;
			}
		}
		
		return false;
	}

	
}
