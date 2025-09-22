package com.example.servlet;

import com.example.servlet.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employeeDelete")
public class EmployeeDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            EmployeeDAO dao = new EmployeeDAO();
            dao.deleteEmployee(id);

            // Redirect back to employee list
            response.sendRedirect("employeeList");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
