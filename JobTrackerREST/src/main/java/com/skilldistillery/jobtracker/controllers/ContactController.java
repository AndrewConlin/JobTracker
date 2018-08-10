package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.Contact;
import com.skilldistillery.jobtracker.services.ContactService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping(path="jobs/{jid}/contacts", method=RequestMethod.GET)
	public List<Contact> indexByJob(@PathVariable int jid){
		return contactService.findContactsForJob(jid);
	}
	
	@RequestMapping(path="jobs/{jid}/contacts/{contactId}", method=RequestMethod.GET)
	public Contact show(@PathVariable int contactId){
		return contactService.findById(contactId);
	}
	
	@RequestMapping(path="jobs/{jid}/contacts", method=RequestMethod.POST)
	public Contact create(@PathVariable int jid, @RequestBody Contact contact){
		return contactService.create(contact, jid);
	}
	
	@RequestMapping(path="jobs/{jid}/contacts/{contactId}", method=RequestMethod.PATCH)
	public Contact update(@PathVariable int contactId,  @RequestBody Contact contact){
		return contactService.update(contact, contactId);
	}
	
	
	@RequestMapping(path="jobs/{jid}/contacts/{contactId}", method=RequestMethod.DELETE)
	public Boolean update(@PathVariable int contactId){
		return contactService.destroy(contactId);
	}
}
