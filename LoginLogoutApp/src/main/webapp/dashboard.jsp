
<%
if (session == null || session.getAttribute("username") == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
</head>
<body style="text-align: center;">
<div style="width: 300px; margin: 0 auto;">
	<h2 style="text-align: center; color: green;">
		Welcome,
		<%=session.getAttribute("username")%>!
	</h2>
	<p style="color: blue;">This is your Dashboard page.</p>
	<a href="logout" style="color: red;">Logout</a>
	</div>
</body>
</html>
