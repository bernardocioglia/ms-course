package com.devsuperior.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.devsuperior.hrpayroll.entity.Payment;

@Service
public class PaymentService {

	public Payment getPayment(final long workerId, final int days) {
		return new Payment("Bob", 200.0, days);
	}

}
