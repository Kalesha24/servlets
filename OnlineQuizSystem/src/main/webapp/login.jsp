<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Login</title>
</head>
<body>
	<h2>Login</h2>

	<% 
        String msg = request.getParameter("message");
        if ("logout".equals(msg)) { 
    %>
	<p style="color: green;">✅ You have logged out successfully.</p>
	<% 
        } else if ("invalid".equals(msg)) { 
    %>
	<p style="color: red;">❌ Invalid UserName or password.</p>
	<% } %>

	<form action="login" method="post">
		UserName: <input type="text" name="username" required><br>
		<br> Password: <input type="password" name="password" required><br>
		<br> <input type="submit" value="Login">
	</form>
</body>
</html>
