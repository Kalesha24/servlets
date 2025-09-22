package com.example.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
	private static final String JDBC_URL = "jdbc:mysql://localhost:8888/studentdb";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "server";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String course = request.getParameter("course");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

			String sql = "INSERT INTO studentsinfo (name, email, course) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, course);

			ps.executeUpdate();
			ps.close();
			con.close();

			// Redirect to list students page
			response.sendRedirect("listStudents");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
