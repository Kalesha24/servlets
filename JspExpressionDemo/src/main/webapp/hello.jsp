<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expression Tag Example</title>
</head>
<body text="blue" bgcolor="wheat">
	<div style="width: 600px; margin: 0 auto;">
		<marquee>
			<h2 style="color: green">Practicing expression tag in eclipse</h2>
		</marquee>
		<center>
			<%
			// Java code inside scriplet tag
			String name = "Kalesha";
			int num1 = 15;
			int num2 = 10;
			%>

			<!-- Expression Tag -->
			<p>
				Welcome
				<%=name%>
			</p>
			<p>
				sum of
				<%=num1%>
				and
				<%=num2%>
				is
				<%=num1 + num2%></p>
		</center>
	</div>
</body>
</html>