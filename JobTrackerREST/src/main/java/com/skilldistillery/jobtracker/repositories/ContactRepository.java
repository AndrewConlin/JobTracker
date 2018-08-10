package com.skilldistillery.jobtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
	List<Contact> findByJobId(int id);
}
