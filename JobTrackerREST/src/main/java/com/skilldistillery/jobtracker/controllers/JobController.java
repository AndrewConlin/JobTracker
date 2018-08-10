package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.Job;
import com.skilldistillery.jobtracker.services.JobService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@RequestMapping(path="boards/{bid}/jobs", method=RequestMethod.GET)
	public List<Job> index(@PathVariable int bid){
		return jobService.findAllJobsByBoardId(bid);
	}
	
	@RequestMapping(path="boards/{bid}/jobs/{jid}", method=RequestMethod.GET)
	public Job show(@PathVariable int bid, @PathVariable int jid) {
		return jobService.findById(jid);
	}
	
	@RequestMapping(path="boards/{bid}/jobs", method=RequestMethod.POST)
	public Job show(@PathVariable int bid, @RequestBody Job job) {
		return jobService.create(job, bid);
	}
	
	@RequestMapping(path="boards/{bid}/jobs/{jid}", method=RequestMethod.PATCH)
	public Job update(@PathVariable int bid, @PathVariable int jid, @RequestBody Job job) {
		return jobService.update(job, jid);
	}
	
	@RequestMapping(path="boards/{bid}/jobs/{jid}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int bid, @PathVariable int jid) {
		return jobService.destroyJob(jid, bid);
	}
}
