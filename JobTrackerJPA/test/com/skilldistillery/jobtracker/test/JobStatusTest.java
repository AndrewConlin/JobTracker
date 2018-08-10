package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.JobStatus;

class JobStatusTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private JobStatus status;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		status = em.find(JobStatus.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_status_scalar() {
		assertEquals("Interested", status.getStatus());
	}

}
