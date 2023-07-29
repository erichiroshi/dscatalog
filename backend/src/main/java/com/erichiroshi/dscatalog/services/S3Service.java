package com.erichiroshi.dscatalog.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			log.info("Upload start");
			s3client.putObject(new PutObjectRequest(bucketName, "test.jpg", file));
			log.info("Upload end");
		} catch (AmazonServiceException e) {
			log.info("AmazonServiceException: " + e.getErrorMessage());
			log.info("Status code: " + e.getErrorCode());
		} catch (AmazonClientException e) {
			log.info("AmazonClientException: " + e.getMessage());
		}
	}
}