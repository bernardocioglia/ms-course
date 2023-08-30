package com.devsuperior.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.entity.Payment;
import com.devsuperior.hrpayroll.entity.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeingClient;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeingClient workerFeingClient;

	public Payment getPayment(final long workerId, final int days) {
		final Worker worker = this.workerFeingClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
