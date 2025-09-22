package com.example.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private static final String JDBC_URL = "jdbc:mysql://localhost:8888/employeedb?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "server";

	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employees = new ArrayList<>();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

		while (rs.next()) {
			employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"),
					rs.getDouble("salary")));
		}

		rs.close();
		stmt.close();
		con.close();

		return employees;
	}

	public void addEmployee(Employee employee) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

		String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, employee.getName());
		ps.setString(2, employee.getDepartment());
		ps.setDouble(3, employee.getSalary());

		ps.executeUpdate();

		ps.close();
		con.close();
	}

	public Employee getEmployeeById(int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

		String sql = "SELECT * FROM employees WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		Employee emp = null;
		if (rs.next()) {
			emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"),
					rs.getDouble("salary"));
		}

		rs.close();
		ps.close();
		con.close();

		return emp;
	}

	public void updateEmployee(Employee employee) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

		String sql = "UPDATE employees SET name=?, department=?, salary=? WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, employee.getName());
		ps.setString(2, employee.getDepartment());
		ps.setDouble(3, employee.getSalary());
		ps.setInt(4, employee.getId());

		ps.executeUpdate();

		ps.close();
		con.close();
	}

	public void deleteEmployee(int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

		String sql = "DELETE FROM employees WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ps.executeUpdate();

		ps.close();
		con.close();
	}

}
