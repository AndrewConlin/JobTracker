package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
