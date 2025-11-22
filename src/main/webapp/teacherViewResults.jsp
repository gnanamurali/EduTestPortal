<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Results</title>
<link rel="stylesheet" href="CSS/tablePages.css">
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1>Results for Quizzes Created by ${teacherName}</h1>

      <c:if test="${not empty message}">
        <div class="success-msg">${message}</div>
      </c:if>

      <c:choose>
        <c:when test="${empty quizList}">
          <p class="no-data">No quizzes found. You haven't created any quizzes yet.</p>
        </c:when>

        <c:otherwise>
          <table class="styled-table">
            <thead>
              <tr>
                <th>Quiz ID</th>
                <th>Title</th>
                <th>Subject</th>
                <th>Created At</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="quiz" items="${quizList}">
                <tr>
                  <td>${quiz.qid}</td>
                  <td>${quiz.title}</td>
                  <td>${quiz.subject}</td>
                  <td>${quiz.createdAt}</td>
                  <td>
                    <a href="quizResults?qid=${quiz.qid}" class="btn result-btn">View Results</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:otherwise>
      </c:choose>
    </div>

    <!-- Button Section -->
    <div class="button-group">
      <a href="teacherDashboard.jsp" class="back-btn">← Back to Dashboard</a>
      <a href="viewQuizzes" class="back-btn secondary-btn">Back to Your Quizzes</a>
    </div>
  </div>
</body>
</html>
