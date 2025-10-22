<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Quizzes</title>
<link rel="stylesheet" href="CSS/tablePages.css">
</head>
<body>
    <jsp:include page="/includes/header.jsp" />

    <div class="page-container">
        <div class="content-card">
            <h1>All Quizzes</h1>

            <c:if test="${not empty param.msg}">
                <div class="success-msg">${param.msg}</div>
            </c:if>

            <c:choose>
                <c:when test="${not empty quizList}">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>Quiz ID</th>
                                <th>Title</th>
                                <th>Subject</th>
                                <th>Teacher</th>
                                <th>Created At</th>
                                <th>Visibility</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="q" items="${quizList}">
                                <tr>
                                    <td>${q.qid}</td>
                                    <td>${q.title}</td>
                                    <td>${q.subject}</td>
                                    <td>${q.teacherName}</td>
                                    <td>${q.createdAt}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${q.visible}">
                                                <a href="ToggleQuizVisibility?qid=${q.qid}&visible=false" class="btn hide-btn">
                                                    Hide
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="ToggleQuizVisibility?qid=${q.qid}&visible=true" class="btn show-btn">
                                                    Show
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="quizResults?qid=${q.qid}" class="btn result-btn">View Results</a>
                                        <a href="deleteQuiz?qid=${q.qid}" 
                                           class="btn delete-btn"
                                           onclick="return confirm('Are you sure you want to delete this quiz?');">
                                           Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <p class="no-data">No quizzes available.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="back-btn-container">
            <a href="adminDashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
