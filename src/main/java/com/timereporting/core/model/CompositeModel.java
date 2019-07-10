package com.timereporting.core.model;

import java.io.Serializable;

public class CompositeModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int referencePk;
	private String year;
	private String month;
	
	public CompositeModel() {}
	
	public CompositeModel(int referencePk, String year, String month) {
		this.referencePk = referencePk;
		this.year = year;
		this.month = month;
	}
	
	public int getReferencePk() {
		return referencePk;
	}
	
	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public void setReferencePk(int referencePk) {
		this.referencePk = referencePk;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + referencePk;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeModel other = (CompositeModel) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (referencePk != other.referencePk)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
}
