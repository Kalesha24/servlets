<html>
<head>
<title>File Upload</title>
</head>
<body>
	<h2>Please Upload a File</h2>

	<% if (request.getAttribute("message") != null) { %>
	<p><%= request.getAttribute("message") %></p>
	<% } %>

	<form method="post" action="upload" enctype="multipart/form-data">
		<input type="file" name="file" multiple />
		<button type="submit">Upload</button>
	</form>

	<br>
	<a href="list.jsp">View Uploaded Files</a>
</body>
</html>
