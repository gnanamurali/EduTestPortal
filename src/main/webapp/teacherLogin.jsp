<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login to your EduTestPortal Teacher's account</h1>

    <form action="loginTeacher" method="post">
     <label for="userEmail">Email:</label>
     <input type="email" id="userEmail" name="userEmail" required><br><br>

     <label for="userPass">Password:</label>
     <input type="password" id="userPass" name="userPass" required><br><br>

     <input type="submit" value="Login">
    </form><br>
    <a href="teacherRegister.jsp">Dont have an account Create your new Teacher's account</a>
</body>
</html>