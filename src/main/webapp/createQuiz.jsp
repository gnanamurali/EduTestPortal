<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
    
    if (session.getAttribute("currentTeacher") == null) {
        response.sendRedirect("teacherLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create new quiz ${currentTeacher.name}</title>
</head>
<body>
	${message}<br>
	<h1>New quiz by ${currentTeacher.name}</h1>
	<form action="createQuiz" method="post">
        <label for="quizSub">Quiz Subject:</label>
        <input type="text" name="quizSubject" value="${currentTeacher.subject}" readonly ><br><br>

		<label for="quizTitle">Quiz Title:</label>
        <input type="text" id="quizTitle" name="quizTitle" required><br><br>
        
        <label for="quizDuration">Duration (in minutes):</label>
		<input type="number" id="quizDuration" name="quizDuration" min="1" value="10" required><br><br>
        

        <label>Assign to Batches:</label><br>
        <input type="checkbox" name="batch" value="B1"> B1<br>
        <input type="checkbox" name="batch" value="B2"> B2<br>
        <input type="checkbox" name="batch" value="B3"> B3<br><br>

        <input type="submit" value="Create Quiz">
	</form>
</body>
</html>