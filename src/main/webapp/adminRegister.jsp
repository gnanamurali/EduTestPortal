<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Admin Account</title>
</head>
<body>
    <h1>Create your EduTestPortal Admin Account</h1>
    
    <form action="adminRegister" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Create Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Register Admin">
    </form>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p style="color:red;"><%= message %></p>
    <%
        }
    %>

    <p><a href="admin_login.jsp">Already have an account? Login here</a></p>
</body>
</html>
