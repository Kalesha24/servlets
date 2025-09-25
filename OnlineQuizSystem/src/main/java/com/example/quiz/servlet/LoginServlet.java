package com.example.quiz.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1️⃣ Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2️⃣ Connect to MySQL (non-SSL for local dev)
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:8888/quizdb?useSSL=false&allowPublicKeyRetrieval=true",
                "root", "server"); // <-- replace with your MySQL password

            // 3️⃣ Prepare query
            ps = conn.prepareStatement(
                "SELECT id, username FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            // 4️⃣ Execute query
            rs = ps.executeQuery();

            if (rs.next()) {
                // ✅ Successful login → set session attributes
                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("username", rs.getString("username"));

                response.sendRedirect("history.jsp"); // go to history page
            } else {
                // ❌ Invalid login → redirect with message
                response.sendRedirect("login.jsp?message=invalid");
            }

        } catch (ClassNotFoundException e) {
            // Driver not found
            e.printStackTrace();
            throw new ServletException("MySQL Driver not found!", e);

        } catch (SQLException e) {
            // Database connection or query failed
            e.printStackTrace(); // <-- prints full root cause in Tomcat console
            throw new ServletException("Database connection or query failed!", e);

        } finally {
            // 5️⃣ Close resources
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }
}
