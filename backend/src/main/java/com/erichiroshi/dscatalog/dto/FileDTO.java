package com.erichiroshi.dscatalog.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDTO implements Serializable {

	private MultipartFile file;
}