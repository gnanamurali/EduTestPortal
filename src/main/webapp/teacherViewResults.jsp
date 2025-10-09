<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Quizzes</title>
</head>
<body>
<h1>Results for Quizzes Created by ${teacherName}</h1>

<c:if test="${not empty message}">
    <p style="color: green;">${message}</p>
</c:if>

<c:if test="${empty quizList}">
    <p>No quizzes found. You haven't created any quizzes yet.</p>
</c:if>

<c:if test="${not empty quizList}">
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Quiz ID</th>
            <th>Title</th>
            <th>Subject</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="quiz" items="${quizList}">
            <tr>
                <td>${quiz.qid}</td>
                <td>${quiz.title}</td>
                <td>${quiz.subject}</td>
                <td>${quiz.createdAt}</td>
                <td>
                    <a href="quizResults?qid=${quiz.qid}">View Results</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<a href="teacherDashboard.jsp">Back to Dashboard</a>
</body>
</html>