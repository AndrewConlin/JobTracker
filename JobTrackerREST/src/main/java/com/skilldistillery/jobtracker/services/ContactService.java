package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entites.Contact;

public interface ContactService {
	List<Contact> findContactsForJob(int jid);
	Contact findById(int contactId);
	Contact create(Contact contact, int jid);
	Contact update(Contact contact, int contactId);
	Boolean destroy(int contactId);
}
