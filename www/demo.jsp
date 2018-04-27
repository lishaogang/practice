<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<%
		String msg = (String)session.getAttribute("msg");
		if(msg!=null)
		{
			out.println("<h3>you are not allowed to login.<h3>");
			out.println(msg);
		}
	 %>
	<form action="verify.jsp">
		name<input type="email" name="name">
		<br>
		word<input type="password" name="pwd">
		<br>
		<input type="submit" name="submit">
	</form>

</body>
</html>