<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Quizzes</title>
</head>
<body>
<h1>Available quizzes for Batch:${batch}</h1>
	<%
    String msg = (String) session.getAttribute("message");
    if (msg != null) {
	%>
    <p><%= msg %></p>
	<%
        session.removeAttribute("message"); 
    }
	%>
	<c:if test="${empty quizzes}">
  	<p>No quizzes available for you right now..</p>
	</c:if>
<c:if test="${not empty quizzes}">
<ul>
    <c:forEach var="quiz" items="${quizzes}">
    <li>
      <strong>${quiz.title}</strong> - ${quiz.subject} 
      (Created: ${quiz.createdAt}) 
      <a href="startQuiz?qid=${quiz.qid}">Start Quiz</a>
    </li>
  </c:forEach>
</ul>
</c:if>

</body>
</html>