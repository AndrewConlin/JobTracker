package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Note;

class NoteTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Note note;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		note = em.find(Note.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_note_scalar() {
		assertEquals("this job is decent", note.getContent());
		assertEquals("2018-07-31", note.getCreateDate().toString());
	}
	
	@Test
	void test_note_job() {
		assertEquals("Instructor", note.getJob().getTitle());
	}

}
