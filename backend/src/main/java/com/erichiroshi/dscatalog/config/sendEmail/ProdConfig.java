package com.erichiroshi.dscatalog.config.sendEmail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.erichiroshi.dscatalog.services.EmailService;
import com.erichiroshi.dscatalog.services.MailjetEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Bean
	EmailService emailService() {
		return new MailjetEmailService();
	}
}