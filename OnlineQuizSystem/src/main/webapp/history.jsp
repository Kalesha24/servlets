<%@ page import="java.sql.*,java.util.*"%>
<%
    Integer userIdObj = (Integer) session.getAttribute("userId");
    String uname = (String) session.getAttribute("username");

    if (userIdObj == null || uname == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int userId = userIdObj;
%>
<html>
<head>
    <title>History</title>
</head>
<body>
    <h2><%= uname %>'s Quiz History</h2>
    <a href="logout">Logout</a> | <a href="quiz.jsp">Take Quiz Again</a>
    <br><br>

    <table border="1">
        <tr>
            <th>Date</th>
            <th>Score</th>
        </tr>
        <%
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                // ✅ Non-SSL URL
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8888/quizdb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "server");

                ps = conn.prepareStatement(
                    "SELECT quiz_date, score, total FROM results WHERE user_id=? ORDER BY quiz_date DESC");
                ps.setInt(1, userId);

                rs = ps.executeQuery();
                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getTimestamp("quiz_date") %></td>
            <td><%= rs.getInt("score") %> / <%= rs.getInt("total") %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
                try { if (ps != null) ps.close(); } catch (SQLException ignored) {}
                try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
            }
        %>
    </table>
</body>
</html>
