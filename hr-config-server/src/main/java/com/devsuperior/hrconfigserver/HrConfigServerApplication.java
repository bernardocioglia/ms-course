package com.devsuperior.hrconfigserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class HrConfigServerApplication implements CommandLineRunner {

	public static void main(final String[] args) {
		SpringApplication.run(HrConfigServerApplication.class, args);
	}

	@Value("${spring.cloud.config.server.git.username}")
	private String username;

	@Override
	public void run(final String... args) throws Exception {
		// System.out.println("USERNAME = " + this.username);

	}

}
