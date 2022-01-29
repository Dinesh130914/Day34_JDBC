package com.jdbc.employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mode.EmpPayrollData;

public class EmployeePayrollService {
	SqlQuries sql = new SqlQuries();
	List<EmpPayrollData> emplist = new ArrayList<>();

	public static void main(String[] args) {
		Connection con = DatabaseConnector.getconnection();
		EmployeePayrollService empservice = new EmployeePayrollService();
		empservice.countEmployees(con);
		empservice.getTotalSalary(con);
		// empservice.getEmpList(con);
		// empservice.getEmpDataonDateWithParticularRange("2016-02-14-", "2021-01-27",
		// con);
	}

	public List<EmpPayrollData> getEmpList(Connection con) {

		try {
			Statement selectstatement = con.createStatement();
			ResultSet rs = selectstatement.executeQuery(sql.GET_EMPLOYEE_QUERY);
			while (rs.next()) {
				EmpPayrollData emp_model = new EmpPayrollData();
				emp_model.setEmp_id(rs.getInt("id"));
				emp_model.setName(rs.getString("name"));
				emp_model.setGender(rs.getString("gender"));
				emp_model.setSalary(rs.getDouble("salary"));
				emp_model.setStart(rs.getDate("start_date"));
				emplist.add(emp_model);
			}
			emplist.forEach(emp -> {
				System.out.println("Emp Id : " + emp.getEmp_id());
				System.out.println("Emp Name : " + emp.getName());
				System.out.println("Salary :" + emp.getSalary());
				System.out.println("Gender : " + emp.getGender());
				System.out.println("Date of Joining : " + emp.getStart());
				System.out.println("----------------------------------------------------");
			});
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emplist;

	}

	public int updatesalary(double salary, String name, Connection con) {
		int sts = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql.UPDATE_SALARY);

			ps.setDouble(1, salary);
			ps.setString(2, name);
			sts = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sts;
	}

	public List<EmpPayrollData> getEmpDataonDateWithParticularRange(String start, String end, Connection con) {
		try {
			EmpPayrollData rangeData = new EmpPayrollData();
			PreparedStatement ps = con.prepareStatement(sql.DATE_RANGE_EMP_DATA);
			ResultSet dateRs = ps.executeQuery();
			while (dateRs.next()) {
				rangeData.setEmp_id(dateRs.getInt("id"));
				rangeData.setName(dateRs.getString("name"));
				rangeData.setGender(dateRs.getString("gender"));
				rangeData.setStart(dateRs.getDate("start_date"));
				rangeData.setSalary(dateRs.getDouble("salary"));
			}
			emplist.forEach(emp -> {
				System.out.println("Emp_Id" + emp.getEmp_id());
				System.out.println("Name" + emp.getName());
				System.out.println("Gender" + emp.getGender());
				System.out.println("Salary" + emp.getSalary());
				System.out.println("Start_Date" + emp.getStart());
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emplist;

	}

	public void countEmployees(Connection con) {
		int maleEmpCount = 0, femaleEmpCount = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select gender,count(*) from employee_payroll group by gender");
			while (rs.next()) {
				if (rs.getString("gender").equals("M")) {
					maleEmpCount = rs.getInt("count(*)");
				} else {
					femaleEmpCount = rs.getInt("count(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Male employees are : " + maleEmpCount);
		System.out.println("Female employees are : " + femaleEmpCount);
	}

	public void getTotalSalary(Connection con) {
		long totalSalary = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select sum(basic_pay) from employee_payroll");
			while (rs.next()) {
				totalSalary = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Total salary paid by organisation : " + totalSalary);
	}
}
