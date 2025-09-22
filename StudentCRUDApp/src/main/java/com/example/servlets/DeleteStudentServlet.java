package com.example.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
	private static final String JDBC_URL = "jdbc:mysql://localhost:8888/studentdb";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "server";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

			PreparedStatement ps = con.prepareStatement("DELETE FROM studentsinfo WHERE id=?");
			ps.setInt(1, Integer.parseInt(id));
			ps.executeUpdate();

			ps.close();
			con.close();

			response.sendRedirect("listStudents");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
