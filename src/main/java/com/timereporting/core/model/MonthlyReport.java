package com.timereporting.core.model;

public class MonthlyReport {
	
	private String year;
	
	private String month;
	
	double hours;
	
	public MonthlyReport(String year, String month, double hours) {
		this.year = year;
		this.month = month;
		this.hours = hours;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public double getHours() {
		return hours;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

}
