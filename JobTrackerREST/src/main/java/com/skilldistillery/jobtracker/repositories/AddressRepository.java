package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
