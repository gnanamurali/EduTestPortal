<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<header style="text-align:right;"><a href="logout">Logout</a></header>
	<h1>hello teacher ${currentTeacher.name}</h1>
<div class="quizButtons">
	<a href="createQuiz.jsp">Create a New Quiz</a>
</div>
</body>
</html>