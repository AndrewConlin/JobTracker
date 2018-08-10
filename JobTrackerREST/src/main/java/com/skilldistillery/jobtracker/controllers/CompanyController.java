package com.skilldistillery.jobtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.Company;
import com.skilldistillery.jobtracker.services.CompanyService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(path="jobs/{id}/companies/{cid}", method=RequestMethod.GET)
	public Company indexByJob(@PathVariable int cid){
		return companyService.findCompanyById(cid);
	}
	
	@RequestMapping(path="jobs/{jid}/companies", method=RequestMethod.POST)
	public Company create(@PathVariable int jid, @RequestBody Company company){
		return companyService.create(company);
	}
	
	@RequestMapping(path="jobs/{jid}/companies/{cid}", method=RequestMethod.PATCH)
	public Company update(@PathVariable int cid,  @RequestBody Company company){
		return companyService.update(company, cid);
	}
	
	
	@RequestMapping(path="jobs/{jid}/companies/{cid}", method=RequestMethod.DELETE)
	public Boolean update(@PathVariable int cid){
		return companyService.delete(cid);
	}
	
}
