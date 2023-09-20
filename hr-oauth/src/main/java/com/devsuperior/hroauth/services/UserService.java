package com.devsuperior.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserFeignClient userFeignClient;

	public User findByEmail(final String email) {
		final User user = this.userFeignClient.findByEmail(email).getBody();
		if (user == null) {
			this.logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		this.logger.info("Email found: " + email);
		return user;
	}
}
