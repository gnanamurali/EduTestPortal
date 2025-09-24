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
<title>Edit quiz</title>
</head>
<body>
	<h1>Editing Quiz:${quiz.title}</h1>
	<form action="updateQuiz" method="post">
		<input type="hidden"  name="qid" value="${quiz.qid}"><br>
		
		<label for="quizTitle">Quiz Title:</label>
		<input type="text" id="quizTitle" name="quizTitle" value="${quiz.title}" required><br>
		
		<label for="quizSub">Quiz Subject:</label>
		<input type="text"  id="quizSub" name="quizSubject" value="${quiz.subject}"><br>
		
		
		<input type="submit" value="Update Quiz">
		 
	</form>
</body>
</html>