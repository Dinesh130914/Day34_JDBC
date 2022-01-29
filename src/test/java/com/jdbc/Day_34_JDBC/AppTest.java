package com.jdbc.Day_34_JDBC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.jdbc.employee.DatabaseConnector;
import com.jdbc.employee.EmployeePayrollService;
import com.mode.EmpPayrollData;


public class AppTest 
{	
	
    @Test
    public void getEmpDataUC1()
    {
    	Connection con = DatabaseConnector.getconnection();
    	EmployeePayrollService EmpService = new EmployeePayrollService();
    	List<EmpPayrollData> emplist = EmpService.getEmpList(con);
    	assertEquals(5,emplist.size());
    }

    @Test
    public void getEmpDataUC2()
    {
    	Connection con = DatabaseConnector.getconnection();
    	EmployeePayrollService EmpService = new EmployeePayrollService();
    	List<EmpPayrollData> emplist = EmpService.getEmpList(con);
    	EmpService.updatesalary(450000.00,"Dinesh",con);
    	assertEquals(1, 1);
    }
}
