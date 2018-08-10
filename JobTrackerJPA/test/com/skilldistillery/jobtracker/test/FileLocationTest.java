package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.FileLocation;

class FileLocationTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private FileLocation location;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		location = em.find(FileLocation.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	@Test
	void test_file_location_scalar() {
		assertEquals("resume", location.getName());
		assertEquals("2018-07-31", location.getUploadDate().toString());
		assertEquals("https://www.livecareer.com/wp-content/uploads/images/uploaded/resume-example-home/web-developer-resume-example-emphasis-2-expanded-2.png", location.getS3Url());
	}

	@Test
	void test_file_location_user() {
		assertEquals("andrew", location.getUser().getUsername());
	}
}
