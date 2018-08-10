package com.skilldistillery.jobtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skilldistillery.jobtracker.entites.FileLocation;
import com.skilldistillery.jobtracker.entites.Job;
import com.skilldistillery.jobtracker.entites.User;
import com.skilldistillery.jobtracker.repositories.FileLocationRepository;
import com.skilldistillery.jobtracker.repositories.JobRepository;
import com.skilldistillery.jobtracker.repositories.UserRepository;
import com.skilldistillery.jobtracker.s3.S3Service;

@Service
public class S3FileServiceImpl implements S3FileService {

	@Autowired
	private S3Service s3;
	
	@Autowired
	private FileLocationRepository fileRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JobRepository jobRepo;
	
	@Override
	public List<FileLocation> getFilesByUsername(String username) {
		return fileRepo.findByUserUsername(username);
	}
	
	@Override
	public List<FileLocation> getFilesByUsernameAndJobId(String username, int jid) {
		return fileRepo.findByUserUsernameAndJobId(username, jid);
	}

	@Override
	public FileLocation createFileForUser(MultipartFile file, String username, String fileName, String description) {
		FileLocation s3File = new FileLocation();
		String s3Url = null;
		User user = userRepo.findByUsername(username);
		
		try {
			 s3Url = s3.uploadFileToS3(user.getUsername() + fileName, file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s3File.setName(fileName);
		s3File.setUser(user);
		s3File.setDescription(description);
		s3File.setS3Url(s3Url);
		s3File.setS3Key(s3Url.substring(s3Url.lastIndexOf("/")+1));
		
		return fileRepo.saveAndFlush(s3File);
	}

	@Override
	public FileLocation createFileForJob(MultipartFile file, String username, String fileName, String description,
			int jobId) {
		FileLocation s3File = new FileLocation();
		String s3Url = null;
		User user = userRepo.findByUsername(username);
		Job job = jobRepo.findById(jobId).get();
		
		try {
			 s3Url = s3.uploadFileToS3(user.getUsername() + fileName, file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s3File.setName(fileName);
		s3File.setUser(user);
		s3File.setJob(job);
		s3File.setDescription(description);
		s3File.setS3Url(s3Url);
		s3File.setS3Key(s3Url.substring(s3Url.lastIndexOf("/")+1));

		return fileRepo.saveAndFlush(s3File);
	}
	
	public void testUpload(String content) {
		s3.testUpload(content);
	}
	
}
