package com.skilldistillery.jobtracker.s3;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.Bucket;

public interface S3Service {
	public String uploadFileToS3(String fileName, MultipartFile file);
	public boolean deleteFileFromS3(String key);
	public void testUpload(String content);
	
//	LEAVE THESE OFF OF THE EXAMPLE
	public List<Bucket> listS3Buckets();
//	public Bucket createS3Bucket(String name);
//	public boolean deleteS3Bucket(String name);
}
