package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.JobStatus;
import com.skilldistillery.jobtracker.services.JobStatusService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class JobStatusController {

	@Autowired
	private JobStatusService jsService;
	
	@RequestMapping(path="jobStatuses", method=RequestMethod.GET)
	public List<JobStatus> index(){
		return jsService.findAll();
	}
}
