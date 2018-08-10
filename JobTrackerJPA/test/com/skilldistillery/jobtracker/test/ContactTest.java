package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Contact;

class ContactTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Contact contact;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		contact = em.find(Contact.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_contact_scalar() {
		assertEquals("Kris", contact.getFirstName());		
		assertEquals("Kane", contact.getLastName());
		assertEquals("30311112434", contact.getPhoneNumber());
		assertEquals("kkane106@gmail.com", contact.getEmail());
		assertEquals("Owner", contact.getPosition());
		assertEquals("guy I know", contact.getDescription());
	}
	
	@Test
	void test_contact_job () {
		assertEquals("Instructor", contact.getJob().getTitle());		
	}

}
