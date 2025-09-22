package com.example.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String JDBC_URL =
        "jdbc:mysql://localhost:8888/userdb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";   // change if needed
    private static final String JDBC_PASS = "server"; // change if needed

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("👉 Form received: " + username + " / " + password);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

            PreparedStatement ps = con.prepareStatement(
                "SELECT id FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Login SUCCESS for: " + username);
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard");
            } else {
                System.out.println("❌ Login FAILED for: " + username);
                response.sendRedirect("error.jsp");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
