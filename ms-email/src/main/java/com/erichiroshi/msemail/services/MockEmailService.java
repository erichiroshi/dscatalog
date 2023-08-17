package com.erichiroshi.msemail.services;

import com.erichiroshi.msemail.dto.EmailDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockEmailService implements EmailService {

	public void senadEmail(EmailDTO dto) {

		log.info("Sending email to: " + dto.getToEmail());
		log.info("Email sent!");

	}
}