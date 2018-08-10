package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.User;

class UserTest {


	private EntityManagerFactory emf;
	private EntityManager em;
	private User user;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_user_scalar() {
		assertEquals("andrew", user.getUsername());
		assertEquals("wombat1", user.getPassword());
		assertEquals("andrew@sd.com", user.getEmail());
		assertEquals("standard", user.getRole());
		assertEquals(true, user.isEnabled());
	}
	
	@Test 
	void test_user_todo() {
		assertEquals("Go Round Mums", user.getTodos().get(0).getTask());
	}
	
	@Test 
	void test_user_board() {
		assertEquals("Job Search (7/31/18)", user.getJobBoards().get(0).getTitle());
	}
	
	@Test 
	void test_user_files() {
		assertEquals("resume", user.getFiles().get(0).getName());
	}
}
