package com.example.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter({ "/employeeList", "/employeeForm.html", "/employeeSave", "/employeeEdit", "/employeeDelete" })
public class AuthFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("username") == null) {
			// Not logged in → redirect to login
			res.sendRedirect("login.html");
		} else {
			// Logged in → continue
			chain.doFilter(request, response);
		}
	}
}
