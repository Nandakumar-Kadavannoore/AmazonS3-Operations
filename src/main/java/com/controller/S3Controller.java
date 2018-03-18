package com.controller;

/**
 * Amazon S3 Operations Controller
 * @author Nandakumar.K
 */
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.serviceImplementation.S3ServiceImplementation;
import com.utils.S3Utils;

@RestController
@RequestMapping("/s3")
@CrossOrigin
public class S3Controller {
	
	@Value("${aws.bucket.name}")
	private String awsBucketName;
	
	@Autowired
	private S3ServiceImplementation s3ServiceImpl;
	
	public static final String UPLOAD_SUCCESS = "Uploaded Successfully";
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(value = "file", required = true) MultipartFile multipartFile) {
		File file = S3Utils.multipartToFile(multipartFile);
		s3ServiceImpl.uploadFile(awsBucketName, file.getName(), file);;
		return new ResponseEntity<>(UPLOAD_SUCCESS, HttpStatus.OK);
	}

}
