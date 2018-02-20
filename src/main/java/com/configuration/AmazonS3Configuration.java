/**
 * Setting Configuration for Amazon S3 
 * @author Nandakumar.K
 */
package com.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.service.S3Service;
import com.serviceImplementation.S3ServiceImplementation;

@Configuration
public class AmazonS3Configuration {
	
	@Value("${aws.secret.key}")
	private String awsSecretKey;
	
	@Value("${aws.access.key}")
	private String awsAccessKey;
	
	@Value("${aws.region}")
	private String awsRegion;
	
	@Bean
	public AmazonS3 amazonS3Client() {
		 BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
	        return AmazonS3ClientBuilder.standard()
	                .withRegion(Regions.fromName(awsRegion))
	                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
	                .build();
	}
	
	@Qualifier("awsS3Service")
    @Bean
    public S3Service awsS3Service(){
        return new S3ServiceImplementation(amazonS3Client());
    }
	
}
