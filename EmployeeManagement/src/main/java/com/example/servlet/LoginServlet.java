package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final String JDBC_URL = "jdbc:mysql://localhost:8888/employeedb?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "server";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Login success → create session
				HttpSession session = request.getSession();
				session.setAttribute("username", username);

				response.sendRedirect("employeeList");
			} else {
				// Login failed → back to login page
				response.getWriter().println("<h3>Invalid username or password</h3>");
				request.getRequestDispatcher("login.html").include(request, response);
			}

			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
