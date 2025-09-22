<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.example.servlet.Employee"%>
<%
    Employee emp = (Employee) request.getAttribute("employee");
%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Employee</title>
</head>
<body>
	<h2>Edit Employee</h2>
	<form action="employeeEdit" method="post">
		<input type="hidden" name="id" value="<%= emp.getId() %>"> <label>Name:</label>
		<input type="text" name="name" value="<%= emp.getName() %>" required><br>
		<br> <label>Department:</label> <input type="text"
			name="department" value="<%= emp.getDepartment() %>" required><br>
		<br> <label>Salary:</label> <input type="number" step="0.01"
			name="salary" value="<%= emp.getSalary() %>" required><br>
		<br>

		<button type="submit">Update Employee</button>
	</form>
	<br>
	<a href="employeeList">⬅ Back to Employee List</a>
</body>
</html>
