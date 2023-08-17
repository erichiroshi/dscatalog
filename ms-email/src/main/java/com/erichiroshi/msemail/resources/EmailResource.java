package com.erichiroshi.msemail.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erichiroshi.msemail.dto.EmailDTO;
import com.erichiroshi.msemail.services.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailResource {

	@Autowired
	private EmailService service;

	@PostMapping
	public ResponseEntity<Void> send(@RequestBody EmailDTO dto) {
		service.senadEmail(dto);
		return ResponseEntity.noContent().build();
	}
}