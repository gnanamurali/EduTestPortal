<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login to your EduTestPortal account</h1>

    <form action="loginServlet" method="post">
     <label for="userEmail">Email:</label>
     <input type="text" id="userEmail" name="userEmail"><br><br>

     <label for="userPass">Password:</label>
     <input type="password" id="userPass" name="userPass"><br><br>

     <input type="submit" value="Login">
    </form><br>
    <a href="register.jsp">New here Create your account</a>
</body>
</html>