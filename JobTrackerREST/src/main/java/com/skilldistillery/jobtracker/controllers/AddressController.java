package com.skilldistillery.jobtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.Address;
import com.skilldistillery.jobtracker.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping(path="addresses/{aid}", method=RequestMethod.GET)
	public Address show(@PathVariable int aid) {
		return addressService.findById(aid);
	}
	
	@RequestMapping(path="addresses", method=RequestMethod.POST)
	public Address create(@RequestBody Address address) {
		return addressService.create(address);
	}
	
	@RequestMapping(path="addresses/{aid}", method=RequestMethod.PATCH)
	public Address update(@PathVariable int aid, @RequestBody Address address) {
		return addressService.update(address, aid);
	}
	
	@RequestMapping(path="addresses/{aid}", method=RequestMethod.DELETE)
	public Boolean destroy(@PathVariable int aid) {
		return addressService.delete(aid);
	}
	 
}
