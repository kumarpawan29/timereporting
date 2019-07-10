package com.timereporting.core.model;

public class LoadMonthYearRequest {
	
	private String action;
	
	private String year;
	
	private String month;

	public String getAction() {
		return action;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
}
