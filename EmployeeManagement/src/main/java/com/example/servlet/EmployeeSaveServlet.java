package com.example.servlet;

import com.example.servlet.EmployeeDAO;
import com.example.servlet.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employeeSave")
public class EmployeeSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));

        Employee newEmp = new Employee(0, name, department, salary);

        try {
            EmployeeDAO dao = new EmployeeDAO();
            dao.addEmployee(newEmp);

            response.sendRedirect("employeeList");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
