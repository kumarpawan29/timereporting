package com.timereporting.core.model;

import java.util.List;

public class LoadMonthYearReponse {
	
	private List<String> years;
	
	private List<String> months;
	
	private String status;

	public List<String> getYears() {
		return years;
	}

	public List<String> getMonths() {
		return months;
	}

	public String getStatus() {
		return status;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
