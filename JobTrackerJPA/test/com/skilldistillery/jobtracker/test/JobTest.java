package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Job;

class JobTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Job job;
	
	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		job = em.find(Job.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	@Test
	void test_job_scalar() {
		assertEquals("Instructor", job.getTitle());
		assertEquals(85000.00, job.getSalary(), 0.1);
		assertEquals("http://www.indeed.com", job.getPostUrl());
		assertEquals("Job teaching Java", job.getDescription());
		assertEquals(null, job.getApplicationDate());
		assertEquals(null, job.getOfferDate());
	}
	
	@Test
	void test_job_board() {
		assertEquals("Job Search (7/31/18)", job.getBoard().getTitle());
	}
	
	@Test
	void test_job_status() {
		assertEquals("Interested", job.getStatus().getStatus());
	}
	
	@Test
	void test_job_notes() {
		assertEquals("this job is decent", job.getNotes().get(0).getContent());
	}
	
	@Test
	void test_job_contacts() {
		assertEquals("Kris", job.getContacts().get(0).getFirstName());
	}
	
	@Test
	void test_job_company() {
		assertEquals("Skill Distillery", job.getCompany().getName());
	}
}
