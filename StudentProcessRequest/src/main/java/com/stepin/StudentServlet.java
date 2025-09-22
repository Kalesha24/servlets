package com.stepin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response); // Delegate GET
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response); // Delegate POST
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set response type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Get form data (works for both GET and POST)
		String name = request.getParameter("name");
		String age = request.getParameter("age");

		// Simple output (you could save this in DB instead)
		out.println("<html><body>");
		out.println("<center>");
		out.println("<h2>Student Details Submitted</h2>");
		out.println("Name: " + name + "<br>");
		out.println("Age: " + age + "<br>");
		out.println("</center>");
		out.println("</body></html>");
	}
}
