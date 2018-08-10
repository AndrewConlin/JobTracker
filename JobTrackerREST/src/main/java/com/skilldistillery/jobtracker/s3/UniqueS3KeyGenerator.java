package com.skilldistillery.jobtracker.s3;

public interface UniqueS3KeyGenerator {
	public String generateKey(String userFileName);
}
