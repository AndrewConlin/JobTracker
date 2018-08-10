package com.skilldistillery.jobtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entites.FileLocation;

public interface FileLocationRepository extends JpaRepository<FileLocation, Integer>{
	List<FileLocation>findByUserUsername(String username);
	List<FileLocation>findByUserUsernameAndJobId(String username, int jid);
}
