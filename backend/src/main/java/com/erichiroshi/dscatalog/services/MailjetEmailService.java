package com.erichiroshi.dscatalog.services;

import org.springframework.beans.factory.annotation.Value;

import com.erichiroshi.dscatalog.dto.EmailDTO;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TrackOpens;
import com.mailjet.client.transactional.TransactionalEmail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailjetEmailService implements EmailService {

	@Value("${spring.mailjet.api-key}")
	private String apiKey;

	@Value("${spring.mailjet.secret-key}")
	private String secretKey;

	public void senadEmail(EmailDTO dto) {

		MailjetClient client = new MailjetClient(ClientOptions.builder()
				.apiKey(apiKey)
				.apiSecretKey(secretKey)
				.build());

		TransactionalEmail message1 = TransactionalEmail.builder()
				.from(new SendContact(dto.getFromEmail(), dto.getFromName()))
				.to(new SendContact(dto.getToEmail(), dto.getToName()))
				.htmlPart(dto.getBody())
				.subject(dto.getSubject())
				.trackOpens(TrackOpens.ENABLED)
				.header("test-header-key", "test-value")
				.customID("custom-id-value")
				.build();

		SendEmailsRequest request = SendEmailsRequest.builder()
				.message(message1) // you can add up to 50 messages per request
				.message(message1)
				.build();

		// act
		try {
			log.info("Enviando email!");
			request.sendWith(client);
			log.info("Email enviado!");
		} catch (MailjetException e) {
			e.printStackTrace();
		}
	}
}