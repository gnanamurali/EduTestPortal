<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create your Account</title>
</head>
<body>
	<h1>Create your EduTestPortal Teacher account</h1>
	<form action="registerTeacher" method="post">
		<label for="userName">Name:</label>
		<input type="text" id="userName" name="userName"><br><br>
        
        <label for="userEmail">Email:</label>
        <input type="email" id="userEmail" name="userEmail" required><br><br>
        
        <label for="userPhone">Phone:</label>
        <input type="text" id="userPhone" name="userPhone"><br><br>
        
        <label for="userPass">Create Password:</label>
        <input type="password" id="userPass" name="userPass"><br><br>
        
        <label for="userSub">Subject:</label>
        <input type="text" id="userSub" name="userSub"><br><br>
        
        
        <input type="submit" value="Create Account">
     </form>
</body>
</html>