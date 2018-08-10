package com.skilldistillery.jobtracker.s3;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3FileManager implements S3Service {
	
	@Autowired
	private AmazonS3 s3;
	
	@Autowired
	private UniqueS3KeyGenerator keyGen;

	@Value("${aws_namecard_bucket}")
	private String bucketName;

	@Override
	public String uploadFileToS3(String fileName, MultipartFile file) {        
		String fileKey = keyGen.generateKey(fileName);

		ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        
		try {
			InputStream fileStream = file.getInputStream();

			s3.putObject(
				new PutObjectRequest(
					this.bucketName, fileKey, fileStream, metadata
				)
				.withCannedAcl(CannedAccessControlList.PublicRead)
			);
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		return getUploadURL(fileKey);
	}

	@Override
	public boolean deleteFileFromS3(String fileName) {
		try {
			s3.deleteObject(this.bucketName, fileName);
			return true;
		} catch(AmazonServiceException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Bucket> listS3Buckets() {
		return s3.listBuckets();
	}
	
	@Override
	public void testUpload(String content) {
        s3.putObject(bucketName, "thisisatestkey1234", "Uploaded String Object");		
	}

	private String getUploadURL(String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append("https://");
		sb.append(this.bucketName);
		sb.append(".s3.amazonaws.com/");
		sb.append(fileName);
		return sb.toString();
	}
}
