<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kalesha JSP</title>
</head>
<body text="blue" bgcolor="wheat">
	<div style="width: 600px; margin: 0 auto;">
		<marquee>
			<h2 style="color: green">Servlets and JSP practice</h2>
		</marquee>
		<center>
			<%
			String s1 = "Hi Kalesha";
			String s2 = "How are you";

			int num1 = 10;
			int num2 = 10;
			%>

			<p><%=s1%>How are you...
			</p>
			<p>
				The sum of
				<%=num1%>
				and
				<%=num2%>
				is
				<%=num1 + num2%></p>
		</center>
	</div>
</body>
</html>