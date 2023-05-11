package com.devsuperior.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient userFeignClient;

	public User findByEmail(final String email) {
		final User user = this.userFeignClient.findByEmail(email).getBody();
		if (user == null) {
			UserService.logger.error("Email not fount: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		UserService.logger.info("Email found: " + email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = this.userFeignClient.findByEmail(username).getBody();
		if (user == null) {
			UserService.logger.error("Email not fount: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		UserService.logger.info("Email found: " + username);
		return user;
	}
}
