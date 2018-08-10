package com.skilldistillery.jobtracker.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.skilldistillery.jobtracker.entites.FileLocation;

public interface S3FileService {
	List<FileLocation> getFilesByUsername(String username);
	List<FileLocation> getFilesByUsernameAndJobId(String username, int jid);
	FileLocation createFileForUser(MultipartFile file, String username, String fileName, String description);
	FileLocation createFileForJob(MultipartFile file, String username, String fileName, String description, int jobId);
	void testUpload(String content);
}
