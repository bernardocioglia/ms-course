package com.devsuperior.hrpayroll.entity;

public class Payment {

	private String name;
	private Double dailyIncome;
	private Integer days;

	public Payment() {
	}

	public Payment(final String name, final Double dailyIncome, final Integer days) {
		this.name = name;
		this.dailyIncome = dailyIncome;
		this.days = days;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Double getDailyIncome() {
		return this.dailyIncome;
	}

	public void setDailyIncome(final Double dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	public Integer getDays() {
		return this.days;
	}

	public void setDays(final Integer days) {
		this.days = days;
	}

	public Double getTotal() {
		return this.days * this.dailyIncome;
	}
}
