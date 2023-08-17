package com.erichiroshi.msoauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erichiroshi.msoauth.entities.User;
import com.erichiroshi.msoauth.feignclients.UserFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserFeignClient userFeignClient;

	public User findByEmail(String email) {
		User user = userFeignClient.findByEmailUser(email).getBody();
		if (user == null) {
			log.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		log.info("Email found: " + email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findByEmailUser(username).getBody();
		if (user == null) {
			log.error("Email not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		log.info("Email found: " + username);
		return user;
	}
}
