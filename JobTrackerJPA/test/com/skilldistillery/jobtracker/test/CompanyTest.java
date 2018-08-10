package com.skilldistillery.jobtracker.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jobtracker.entites.Company;

class CompanyTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Company company;


	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("tracker");
		em = emf.createEntityManager();
		company = em.find(Company.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	
	@Test
	void test_company_scalar() {
		assertEquals("Skill Distillery", company.getName());
		assertEquals("http://www.skilldistillery.com", company.getUrl());
	}
	
	@Test
	void test_company_address() {
		assertEquals("1400 E. Orchard", company.getAddress().getStreet());
	}

}
