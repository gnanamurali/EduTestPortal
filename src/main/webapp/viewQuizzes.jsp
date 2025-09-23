<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.EduTestPortal.model.Quiz" %>

<% List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Quizzes</title>
</head>
<body>
	<p style="color:green">${message}</p>
    <h1>Your Quizzes</h1>
    <table border="1">
        <tr>
    		<th>Quiz ID</th>
    		<th>Quiz Title</th>
    		<th>Subject</th>
    		<th>Created At</th>
    		<th>Actions</th>
        </tr>
    	<%
    		 if (quizzes != null && !quizzes.isEmpty()) 
    		 {
    		 for(Quiz q : quizzes){
    	%>
        <tr>
    		    <td><%=q.getQid() %></td>
    		    <td><%=q.getTitle() %></td>
    		    <td><%=q.getSubject() %></td>
    		    <td><%=q.getCreatedAt() %></td>
    		    <td><a href="deleteQuiz?qid=<%=q.getQid()%>" onclick="return confirm('Are you sure you want to delete this quiz?');">
             Delete Quiz</a></td>
        </tr>
    	<%} 
    	}
    	else 
    	{
    	%>
        <tr>
            <td colspan="4">No quizzes created yet.</td>
        </tr>
   		 <% } %>
    </table>
    
</body>
</html>