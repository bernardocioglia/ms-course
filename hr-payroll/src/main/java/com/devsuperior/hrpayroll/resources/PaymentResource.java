package com.devsuperior.hrpayroll.resources;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrpayroll.entity.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;

	@CircuitBreaker(name = "backendA", fallbackMethod = "getPaymentAlternative")
	@TimeLimiter(name = "backendA", fallbackMethod = "getPaymentAlternative")
	@GetMapping(value = "/{workerId}/days/{days}")
	public CompletableFuture<ResponseEntity<Payment>> getPayment(@PathVariable final Long workerId,
			@PathVariable final Integer days) {
		return CompletableFuture.supplyAsync(() -> {
			final Payment payment = this.service.getPayment(workerId, days);
			final ResponseEntity<Payment> ret = ResponseEntity.ok(payment);
			return ret;
		}); 
	}

	public CompletableFuture<ResponseEntity<Payment>> getPaymentAlternative(final Long workerId, final Integer days,
			final Exception e) {
		final Payment payment = new Payment("Brann", 400.0, days);
		final ResponseEntity<Payment> ret = ResponseEntity.ok(payment);
		return CompletableFuture.completedFuture(ret);
	}

}
