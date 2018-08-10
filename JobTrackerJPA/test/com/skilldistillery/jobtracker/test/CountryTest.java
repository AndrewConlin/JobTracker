package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Country;

class CountryTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Country country;;

	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		country = em.find(Country.class, "US");
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_country_scalar() {
		assertEquals("United States", country.getName());
		assertEquals("US", country.getCountryCode());
	}
}
