<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<%
    String msg = (String) session.getAttribute("message");
    if (msg != null) {
	%>
    <p><%= msg %></p>
	<%
        session.removeAttribute("message"); 
    }
	%>
	<header style="text-align:right;"><a href="logout">Logout</a></header>
	<h1>Hello ${currentTeacher.name}</h1><br><br>
	<h2>Quiz Management</h2><br>
<div class="quizButtons">
<ul>
	<li><a href="createQuiz.jsp">Create a New Quiz</a></li>
	<li><a href="viewQuizzes">View your Quizzes</a></li>
</ul>
</div>
</body>
</html>