package com.jdbc.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mode.EmpPayrollData;

public class EmployeePayrollService {
	SqlQuries sql = new SqlQuries();

	public static void main(String[] args) {
		Connection con = DatabaseConnector.getconnection();
		EmployeePayrollService empservice = new EmployeePayrollService();
		
	}

	public List<EmpPayrollData> getEmpList(Connection con) {
		List<EmpPayrollData> emplist = new ArrayList<>();
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
				System.out.println("Salary :"+emp.getSalary());
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

	public int updatesalary( double salary,String name, Connection con) {
		int sts=0;
		try {
			PreparedStatement ps = con.prepareStatement(sql.UPDATE_SALARY);
			
			ps.setDouble(1, salary);
			ps.setString(2, name);
			sts=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sts;
	}

}
