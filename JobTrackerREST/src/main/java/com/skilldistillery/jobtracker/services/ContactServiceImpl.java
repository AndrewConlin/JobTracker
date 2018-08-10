package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.Contact;
import com.skilldistillery.jobtracker.entites.Job;
import com.skilldistillery.jobtracker.repositories.ContactRepository;
import com.skilldistillery.jobtracker.repositories.JobRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private JobRepository jobRepo;

	@Override
	public List<Contact> findContactsForJob(int jid) {
		return contactRepo.findByJobId(jid);
	}

	@Override
	public Contact findById(int contactId) {
		Optional<Contact> optCon = contactRepo.findById(contactId);
		
		if(optCon.isPresent()) {
			Contact contact = optCon.get();
			return contact;
		}
		return null;
	}

	@Override
	public Contact create(Contact contact, int jid) {
		Optional<Job> optJob = jobRepo.findById(jid);
		
		if(optJob.isPresent()) {
			Job job = optJob.get();
			contact.setJob(job);
			return contactRepo.saveAndFlush(contact);
		}
		return null;
	}

	@Override
	public Contact update(Contact contact, int contactId) {
		Optional<Contact> optCon = contactRepo.findById(contactId);
		
		if(optCon.isPresent()) {
			Contact managed = optCon.get();
			managed.setFirstName(contact.getFirstName());
			managed.setLastName(contact.getLastName());
			managed.setDescription(contact.getDescription());
			managed.setPhoneNumber(contact.getPhoneNumber());
			managed.setEmail(contact.getEmail());
			managed.setPosition(contact.getPosition());
			
			return contactRepo.saveAndFlush(contact);
		}
		return null;
	}

	@Override
	public Boolean destroy(int contactId) {
		Optional<Contact> optCon = contactRepo.findById(contactId);
		
		if(optCon.isPresent()) {
			Contact contact = optCon.get();
			contactRepo.delete(contact);
			return true;
		}		
		return false;
	}

}
