<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
<%
	String name = (String)session.getAttribute("admin");
	if(name == null)
	{

	response.sendRedirect("demo.jsp");
	}
%>
<p>You secceeded login</p>
</body>
</html>