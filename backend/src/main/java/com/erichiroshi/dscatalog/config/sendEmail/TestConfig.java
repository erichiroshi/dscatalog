package com.erichiroshi.dscatalog.config.sendEmail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.erichiroshi.dscatalog.services.EmailService;
import com.erichiroshi.dscatalog.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	EmailService emailService() {
		return new MockEmailService();
	}
}