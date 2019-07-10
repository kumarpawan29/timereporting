package com.timereporting.core.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(CompositeModel.class)
@Table(name = "Hours")
public class Hours {
	
	@Id
	@Column(name = "ReferencePK")
	private int referencePk;
	
	@Id
	@Column(name = "Month", columnDefinition = "VARCHAR(100)")
    @NotEmpty
    private String month;
	
	@Id
	@Column(name = "Year", columnDefinition = "VARCHAR(100)")
    @NotEmpty
    private String year;
	
	@Column(name = "Submittingdate")
	@Temporal(TemporalType.DATE)
	private Date submittingDate;
	
	@Column(name = "Totalhours")
	@NotNull
	private double totalhours;
	
	@Column(name = "Week1")
	@NotNull
	private double week1;
	
	@Column(name = "Week2")
	@NotNull
	private double week2;
	
	@Column(name = "Week3")
	@NotNull
	private double week3;
	
	@Column(name = "Week4")
	@NotNull
	private double week4	;

	public int getReferencePk() {
		return referencePk;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public Date getSubmittingDate() {
		return submittingDate;
	}

	public double getTotalhours() {
		return totalhours;
	}

	public double getWeek1() {
		return week1;
	}

	public double getWeek2() {
		return week2;
	}

	public double getWeek3() {
		return week3;
	}

	public double getWeek4() {
		return week4;
	}

	public void setReferencePk(int referencePk) {
		this.referencePk = referencePk;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setSubmittingDate(Date submittingDate) {
		this.submittingDate = submittingDate;
	}

	public void setTotalhours(double totalhours) {
		this.totalhours = totalhours;
	}

	public void setWeek1(double week1) {
		this.week1 = week1;
	}

	public void setWeek2(double week2) {
		this.week2 = week2;
	}

	public void setWeek3(double week3) {
		this.week3 = week3;
	}

	public void setWeek4(double week4) {
		this.week4 = week4;
	}
}
