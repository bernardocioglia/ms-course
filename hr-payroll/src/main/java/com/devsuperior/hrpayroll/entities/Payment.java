package com.devsuperior.hrpayroll.entities;

import java.io.Serializable;

public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private Double dailyIncome;
	private Integer days;

	public Payment() {
		super();
	}

	public Payment(final String name, final Double dailyIncome, final Integer days) {
		super();
		this.name = name;
		this.dailyIncome = dailyIncome;
		this.days = days;
	}

	public Double getDailyIncome() {
		return this.dailyIncome;
	}

	public Integer getDays() {
		return this.days;
	}

	public String getName() {
		return this.name;
	}

	public double getTotal() {
		return this.days * this.dailyIncome;
	}

	public void setDailyIncome(final Double dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	public void setDays(final Integer days) {
		this.days = days;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
