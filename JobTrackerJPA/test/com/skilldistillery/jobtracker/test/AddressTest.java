package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Address;

class AddressTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Address a;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		a = em.find(Address.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_address_scalar() {
		assertEquals("2025 S. Gilpin St.", a.getStreet());
		assertEquals(null, a.getStreet2());
		assertEquals("Denver", a.getCity());
		assertEquals("CO", a.getState());
		assertEquals("80210", a.getZipCode());
		
	}
	
	@Test
	void test_address_country() {
		assertEquals("United States", a.getCountry().getName());
	}

}
