package com.skilldistillery.jobtracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilldistillery.jobtracker.data.FileDTO;
import com.skilldistillery.jobtracker.entites.FileLocation;
import com.skilldistillery.jobtracker.services.S3FileService;

@RestController
@RequestMapping("aws/s3")
public class S3FileController {
	@Autowired
	private S3FileService s3FileService;
	
	@RequestMapping(path = "/files", method = RequestMethod.GET)
	public List<FileLocation> getFilesForUser(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return s3FileService.getFilesByUsername(principal.getName());
	}

	@RequestMapping(path = "job/{jid}/files", method = RequestMethod.GET)
	public List<FileLocation> getFilesForJob(@PathVariable int jid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return s3FileService.getFilesByUsernameAndJobId(principal.getName(), jid);
	}

	
	@RequestMapping(path = "/upload/file", method = RequestMethod.POST)
	public FileLocation uploadFile(
				HttpServletRequest req,
				HttpServletResponse res,
				@RequestParam("file") MultipartFile file,
				@RequestParam("data") String dataJSON,
				Principal principal
						  ) {
		FileDTO data = null;
		ObjectMapper om = new ObjectMapper();
		try {
			data = om.readValue(dataJSON, FileDTO.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(data.getJobId() != 0) {
			return s3FileService.createFileForJob(file, principal.getName(), file.getOriginalFilename(), data.getDescription(), data.getJobId());
		}
		return s3FileService.createFileForUser(file, principal.getName(), file.getOriginalFilename(), data.getDescription());
	}
	
	@RequestMapping(path = "/text", method = RequestMethod.POST)
	public void testUpload(@RequestBody String data) {
		s3FileService.testUpload(data);
	}
	
//	private File multipartToFile(MultipartFile multipart) {
//		try {
//			File convFile = new File(multipart.getOriginalFilename());
//			multipart.transferTo(convFile);
//			return convFile;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
