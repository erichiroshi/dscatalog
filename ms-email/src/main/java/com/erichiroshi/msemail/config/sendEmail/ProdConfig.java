package com.erichiroshi.msemail.config.sendEmail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.erichiroshi.msemail.services.EmailService;
import com.erichiroshi.msemail.services.MailjetEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Bean
	EmailService emailService() {
		return new MailjetEmailService();
	}
}