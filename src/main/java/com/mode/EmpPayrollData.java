package com.mode;

import java.util.Date;

public class EmpPayrollData {
	public int emp_id;
	String name;
	String gender;
	Date start;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "EmpPayroll [emp_id=" + emp_id + ", name=" + name + ", gender=" + gender + ", start=" + start + "]";
	}
}
