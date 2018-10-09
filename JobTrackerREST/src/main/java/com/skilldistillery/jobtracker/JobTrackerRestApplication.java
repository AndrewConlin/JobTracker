package com.skilldistillery.jobtracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@SpringBootApplication
public class JobTrackerRestApplication {

	@Value("${aws_access_key_id}")
	private String accessKeyID;
	

	@Value("${aws_secret_access_key}")
	private String secretAccessKey;
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AmazonS3 createAmazonS3Client() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyID, secretAccessKey);

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();	
		
		return s3Client;
	}

	public static void main(String[] args) {
		SpringApplication.run(JobTrackerRestApplication.class, args);
	}
}
