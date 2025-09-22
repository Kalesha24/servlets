package com.example.servlet;

import com.example.servlet.EmployeeDAO;
import com.example.servlet.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employeeEdit")
public class EmployeeEditServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			EmployeeDAO dao = new EmployeeDAO();
			Employee emp = dao.getEmployeeById(id);

			request.setAttribute("employee", emp);
			request.getRequestDispatcher("editEmployee.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		double salary = Double.parseDouble(request.getParameter("salary"));

		Employee emp = new Employee(id, name, department, salary);

		try {
			EmployeeDAO dao = new EmployeeDAO();
			dao.updateEmployee(emp);

			response.sendRedirect("employeeList");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
