package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Board;

class BoardTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Board board;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		board = em.find(Board.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_board_scalar() {
		assertEquals("Job Search (7/31/18)", board.getTitle());
		assertEquals("Finding a dev job", board.getDescription());
		assertEquals("2018-07-31", board.getCreateDate().toString());
	}
	
	@Test
	void test_board_user() {
		assertEquals("andrew", board.getUser().getUsername());
	}
	
	@Test
	void test_board_jobs() {
		assertEquals("Instructor", board.getJobs().get(0).getTitle());
	}
	

}
