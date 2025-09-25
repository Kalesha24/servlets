<%@ page import="java.sql.*"%>
<html>
<head>
<title>Uploaded Files</title>
</head>
<body>
	<h2>Uploaded Files</h2>
	<table border="1">
		<tr>
			<th>Filename</th>
			<th>Size (bytes)</th>
			<th>Upload Date</th>
			<th>Download</th>
		</tr>
		<%
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8888/filedb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "server");

            ps = conn.prepareStatement("SELECT * FROM files ORDER BY upload_date DESC");
            rs = ps.executeQuery();

            while (rs.next()) {
    %>
		<tr>
			<td><%= rs.getString("filename") %></td>
			<td><%= rs.getLong("filesize") %></td>
			<td><%= rs.getTimestamp("upload_date") %></td>
			<td><a href="download?fileId=<%= rs.getInt("id") %>">Download</a></td>
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
	<br>
	<a href="upload.jsp">Upload New File</a>
</body>
</html>
