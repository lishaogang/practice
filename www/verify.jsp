<!DOCTYPE html>
<html>
<head>
	<title>verify</title>
</head>
<body>
	<%
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		if(name.equals("admin@zh.com") && pwd.equals("6666"))
		{
			session.setAttribute("admin",name);
			response.sendRedirect("succeeded.jsp");
		}
		else
		{
			session.setAttribute("msg","Bad password or user name.");
			response.sendRedirect("demo.jsp");
		}
	%>
</body>
</html>