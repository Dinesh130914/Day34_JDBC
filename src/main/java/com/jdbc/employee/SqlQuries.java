package com.jdbc.employee;

public class SqlQuries {
	public String GET_EMPLOYEE_QUERY = "select*from employee_payroll";
	public String UPDATE_SALARY="update employee_payroll set salary=? where name=?;";
}
