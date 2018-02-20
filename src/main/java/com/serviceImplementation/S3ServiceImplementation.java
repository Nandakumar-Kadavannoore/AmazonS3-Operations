/**
 * Service Implementation of Amazon S3 Operations
 * @author Nandakumar.K
 */
package com.serviceImplementation;

import java.io.File;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.service.S3Service;

public class S3ServiceImplementation implements S3Service {
	
	private AmazonS3 s3Client;

	public S3ServiceImplementation(final AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	/**
	 * Used to Upload a file to AWS
	 */
	@Override
	public void uploadFile(String bucketName, String keyName, File file) {
		// Using CannedAcl, Setting the Public Read
		s3Client.putObject(
				new PutObjectRequest(bucketName, keyName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	/**
	 * Used to delete a file from Bucket using given name
	 */
	@Override
	public boolean deleteFromBucket(String bucketName, String keyName) {
		// If required Object exist, it will be deleted else
		// else nothing will happen
		s3Client.deleteObject(bucketName, keyName);
		return true;
	}

}
