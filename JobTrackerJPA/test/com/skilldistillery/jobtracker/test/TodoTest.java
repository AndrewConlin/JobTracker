package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Todo;

class TodoTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Todo todo;
	
	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		todo = em.find(Todo.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	@Test
	void test_todo_scalar() {
		assertEquals("Go Round Mums", todo.getTask());
		assertEquals(null, todo.getDescription());
		assertEquals(false, todo.isCompleted());
		assertEquals(null, todo.getCompleteDate());
		assertEquals("2018-07-31", todo.getCreatedAt().toString());
		assertEquals(null, todo.getDueDate());
	}
	
	@Test 
	void test_todo_user(){
		assertEquals("andrew", todo.getUser().getUsername());

	}

}
