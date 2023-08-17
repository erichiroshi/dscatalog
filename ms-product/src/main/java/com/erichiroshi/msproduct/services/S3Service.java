package com.erichiroshi.msproduct.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URL uploadFile(MultipartFile file) {
		try {
			String orignalName = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(orignalName);
			String fileName = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() + "." + extension;

			InputStream is = file.getInputStream();
			String contentType = file.getContentType();
			return uploadFile(is, fileName, contentType);

		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private URL uploadFile(InputStream is, String fileName, String contentType) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		log.info("Upload start");
		s3client.putObject(bucketName, fileName, is, metadata);
		log.info("Upload finish");
		return s3client.getUrl(bucketName, fileName);
	}
}