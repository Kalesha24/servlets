<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List, com.example.servlet.Employee"%>

<html>
<head>
<title>Employee List</title>
</head>
<body bgcolor="wheat">
	<div style="width: 900px; margin: 0 auto;">
		<marquee>
			<h2 style="color: green";>All Employee's List</h2>
		</marquee>
		<div style="text-align: center;">
			<table border="1" cellpadding="9">
				<tr>
					<th style="background-color: white; color: blue;">ID</th>
					<th style="background-color: white; color: blue;">Name</th>
					<th style="background-color: white; color: blue">Department</th>
					<th style="background-color: white; color: blue">Salary</th>
					<th style="background-color: white; color: blue">Actions</th>
				</tr>

				<%
				List<Employee> employees = (List<Employee>) request.getAttribute("employees");
				if (employees != null && !employees.isEmpty()) {
					for (Employee e : employees) {
				%>
				<tr>
					<td><%=e.getId()%></td>
					<td><%=e.getName()%></td>
					<td><%=e.getDepartment()%></td>
					<td><%=e.getSalary()%></td>
					<td><a href="employeeEdit?id=<%=e.getId()%>"
						style="color: green;>✏️ Edit</a> | <a
						href="employeeDelete?id=<%=e.getId()%>" style="color: red;
						onclick="return confirm('
						Delete thisemployee?')">🗑️ Delete</a></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5">No employees found</td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
		<br> <a href="employeeForm.html">➕ Add New Employee</a> | <a
			href="logout">🚪 Logout</a> <br> <br>
	</div>
</body>
</html>
