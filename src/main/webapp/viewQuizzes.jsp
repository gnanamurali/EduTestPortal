<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.EduTestPortal.model.Quiz" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Quizzes</title>
<link rel="stylesheet" href="CSS/tablePages.css">
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1>Your Quizzes</h1>

      <c:if test="${not empty param.msg}">
        <div class="success-msg">${param.msg}</div>
      </c:if>

      <c:if test="${not empty message}">
        <div class="success-msg">${message}</div>
      </c:if>

      <c:choose>
        <c:when test="${not empty quizList}">
          <table class="styled-table">
            <thead>
              <tr>
                <th>Quiz ID</th>
                <th>Quiz Title</th>
                <th>Subject</th>
                <th>Created At</th>
                <th>Actions</th>
                <th>Visibility</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="q" items="${quizList}">
                <tr>
                  <td>${q.qid}</td>
                  <td>${q.title}</td>
                  <td>${q.subject}</td>
                  <td>${q.createdAt}</td>
                  <td class="action-buttons">
                    <a href="editQuiz?qid=${q.qid}" class="btn edit-btn">Edit</a>
                    <a href="manageQuestions?qid=${q.qid}" class="btn manage-btn">Manage</a>
                    <a href="deleteQuiz?qid=${q.qid}" class="btn delete-btn"
                       onclick="return confirm('Are you sure you want to delete this quiz?');">Delete</a>
                  </td>
                  <td>
 						<c:choose>
    						<c:when test="${q.visible}">
      					<span class="visibility-status visible">Visible</span>
      					<a href="ToggleQuizVisibility?qid=${q.qid}&visible=false&tid=${sessionScope.currentTeacher.tid}" 
         				class="btn hide-btn">Hide</a>
    						</c:when>
    						<c:otherwise>
      					<span class="visibility-status hidden">Hidden</span>
      					<a href="ToggleQuizVisibility?qid=${q.qid}&visible=true&tid=${sessionScope.currentTeacher.tid}" 
         				class="btn show-btn">Show</a>
    						</c:otherwise>
  						</c:choose>
				</td> 
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>

        <c:otherwise>
          <p class="no-data">No quizzes created yet.</p>
        </c:otherwise>
      </c:choose>
    </div>

    <div class="back-btn-container">
      <a href="teacherDashboard.jsp" class="back-btn">← Back to Dashboard</a>
    </div>
  </div>
</body>
</html>
