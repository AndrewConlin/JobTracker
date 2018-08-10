package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
