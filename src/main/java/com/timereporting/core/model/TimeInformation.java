package com.timereporting.core.model;

public class TimeInformation {
	
	private String year;
	
	private String month;
	
	private long hoursForWeek1;
	
	private long minutesForWeek1;
	
	private long hoursForWeek2;
	
	private long minutesForWeek2;
	
	private long hoursForWeek3;
	
	private long minutesForWeek3;
	
	private long hoursForWeek4;
	
	private long minutesForWeek4;

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}
	  
	public long getHoursForWeek1() {
		return hoursForWeek1;
	}

	public long getMinutesForWeek1() {
		return minutesForWeek1;
	}

	public long getHoursForWeek2() {
		return hoursForWeek2;
	}

	public long getMinutesForWeek2() {
		return minutesForWeek2;
	}

	public long getHoursForWeek3() {
		return hoursForWeek3;
	}

	public long getMinutesForWeek3() {
		return minutesForWeek3;
	}

	public long getHoursForWeek4() {
		return hoursForWeek4;
	}

	public long getMinutesForWeek4() {
		return minutesForWeek4;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setHoursForWeek1(long hoursForWeek1) {
		this.hoursForWeek1 = hoursForWeek1;
	}

	public void setMinutesForWeek1(long minutesForWeek1) {
		this.minutesForWeek1 = minutesForWeek1;
	}

	public void setHoursForWeek2(long hoursForWeek2) {
		this.hoursForWeek2 = hoursForWeek2;
	}

	public void setMinutesForWeek2(long minutesForWeek2) {
		this.minutesForWeek2 = minutesForWeek2;
	}

	public void setHoursForWeek3(long hoursForWeek3) {
		this.hoursForWeek3 = hoursForWeek3;
	}

	public void setMinutesForWeek3(long minutesForWeek3) {
		this.minutesForWeek3 = minutesForWeek3;
	}

	public void setHoursForWeek4(long hoursForWeek4) {
		this.hoursForWeek4 = hoursForWeek4;
	}

	public void setMinutesForWeek4(long minutesForWeek4) {
		this.minutesForWeek4 = minutesForWeek4;
	}

	@Override
	public String toString() {
		return "TimeInformation [year=" + year + ", month=" + month + ", hoursForWeek1=" + hoursForWeek1
				+ ", minutesForWeek1=" + minutesForWeek1 + ", hoursForWeek2=" + hoursForWeek2 + ", minutesForWeek2="
				+ minutesForWeek2 + ", hoursForWeek3=" + hoursForWeek3 + ", minutesForWeek3=" + minutesForWeek3
				+ ", hoursForWeek4=" + hoursForWeek4 + ", minutesForWeek4=" + minutesForWeek4 + "]";
	}
}
