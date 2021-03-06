package com.devsuperior.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

	@Value("${spring.profiles.active}")
	private String testConfig;

	@Autowired
	private Environment env;

	@Autowired
	private WorkerRepository repository;

	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		final List<Worker> list = this.repository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable final Long id) {

		WorkerResource.logger.info("PORT = " + this.env.getProperty("local.server.port"));

		final Worker obj = this.repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/configs")
	public ResponseEntity<List<Worker>> getConfigs() {
		WorkerResource.logger.info("CONFIG = " + this.testConfig);
		return ResponseEntity.noContent().build();
	}
}
