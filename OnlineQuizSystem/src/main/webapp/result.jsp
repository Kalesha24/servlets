<%@ page language="java"%>
<html>
<head>
<title>Quiz Result</title>
</head>
<body>
	<h2>Quiz Result</h2>
	<p>
		Hello,
		<%=session.getAttribute("username")%></p>
	<p>
		Your Score:
		<%=request.getAttribute("score")%>
		/
		<%=request.getAttribute("total")%></p>
	<a href="history.jsp">View Past Results</a>
	<br>
	<a href="logout">Logout</a>
</body>
</html>
