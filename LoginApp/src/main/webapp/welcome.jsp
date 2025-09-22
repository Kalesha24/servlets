<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<div style="width: 300px; margin: 0 auto;">
		<h2 style="text-align: center; color: green;">✅ Student
			Registered Successfully!</h2>
		<p style="color: blue;">
			Welcome,
			<%=request.getParameter("username")%></p>
	</div>
</body>
</html>
