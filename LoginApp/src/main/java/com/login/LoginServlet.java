package com.login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println("DEBUG: Username=" + username + ", Password=" + password);

		try {
			// Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DEBUG: Driver Loaded");

			// Connect to DB
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8888/userdb", "root", "server");
			System.out.println("DEBUG: DB Connected");

			// Prepare SQL
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			System.out.println("DEBUG: SQL Prepared");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("DEBUG: User Found");
				RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
				rd.forward(request, response);
			} else {
				System.out.println("DEBUG: Invalid Credentials");
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
