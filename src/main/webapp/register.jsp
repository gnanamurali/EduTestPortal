<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create your Account</title>
</head>
<body>
    <h1>Create your EduTestPortal Student account</h1>
	<form action="registerServlet" method="post">
		<label for="userName">Name:</label>
		<input type="text" id="userName" name="userName"><br><br>
        
        <label for="userEmail">Email:</label>
        <input type="text" id="userEmail" name="userEmail"><br><br>
        
        <label for="userPhone">Phone:</label>
        <input type="text" id="userPhone" name="userPhone"><br><br>
        
        <label for="userPass">Create Password:</label>
        <input type="password" id="userPass" name="userPass"><br><br>
        
        <label for="userDept">Department:</label>
        <input type="text" id="userDept" name="userDept"><br><br>
        
        <label for="userYos">Year of Study:</label>
        <input type="number" id="userYos" name="userYos"><br><br>
        
        <label for="userBatch">Batch:</label>
        <select id="userBatch" name="userBatch">
			<option value="">Select Batch</option>
			<option value="B1">BATCH-1</option>
			<option value="B2">BATCH-2</option>
            <option value="B3">BATCH-3</option>
        </select><br><br>
        <input type="submit" value="Create Account">       	
	</form>
</body>
</html>