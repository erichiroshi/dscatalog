package com.erichiroshi.dscatalog.services;

import com.erichiroshi.dscatalog.dto.EmailDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockEmailService implements EmailService {

	public void senadEmail(EmailDTO dto) {

		log.info("Sending email to: " + dto.getToEmail());
		log.info("Email sent!");

	}
}