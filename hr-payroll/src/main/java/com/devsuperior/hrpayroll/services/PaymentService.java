package com.devsuperior.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.hrpayroll.entity.Payment;
import com.devsuperior.hrpayroll.entity.Worker;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(final long workerId, final int days) {
		final Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", "" + workerId);
		final Worker worker = this.restTemplate.getForObject(this.workerHost + "/workers/{id}", Worker.class,
				uriVariables);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
