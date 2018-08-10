package com.skilldistillery.jobtracker.s3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class UniqueS3KeySHA256Generator implements UniqueS3KeyGenerator {

	@Override
	public String generateKey(String userFileName) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return Base64.getEncoder()
					.encodeToString(
						digest.digest(
							userFileName.getBytes(StandardCharsets.UTF_8)
							)
					)
					.substring(0,24)
					.replaceAll("[^A-Za-z0-9]", "");
	}

}
