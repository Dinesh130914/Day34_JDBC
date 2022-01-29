package com.jdbc.employee;

import java.sql.Connection;
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
		empservice.getEmpList(con);
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
				emp_model.setStart(rs.getDate("start_date"));
				emplist.add(emp_model);
			}
			emplist.forEach(emp -> {
				System.out.println("Emp Id : " + emp.getEmp_id());
				System.out.println("Emp Name : " + emp.getName());

				System.out.println("Gender : " + emp.getGender());
				System.out.println("Date of Joining : " + emp.getStart());
				System.out.println("----------------------------------------------------");
			});
			con.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emplist;

	}
}
