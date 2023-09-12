package com.devsuperior.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

	private static Logger logger = LoggerFactory.getLogger(WorkerRepository.class);

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

		// TimeOut test for
		// resilience4j.timelimiter.instances.backendA.timeout-duration=2s
		/*
		 * try { Thread.sleep(3000L); } catch (final InterruptedException e) {
		 * e.printStackTrace(); }
		 */

		WorkerResource.logger.info("PORT = " + this.env.getProperty("local.server.port"));

		final Worker obj = this.repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}

}
