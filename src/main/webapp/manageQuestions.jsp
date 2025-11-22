<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Questions | ${quiz.title}</title>
<link rel="stylesheet" href="CSS/formPages.css">
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1 class="page-title">Manage Questions for Quiz: ${quiz.title}</h1>

      <!-- Message -->
      <%
        String msg = (String) session.getAttribute("message");
        if (msg != null) {
      %>
          <p class="success-msg"><%= msg %></p>
      <%
          session.removeAttribute("message");
        }
      %>

      <!-- Add New Question Button -->
      <div class="btn-container" style="text-align:right;">
        <a href="addQuestion?qid=${qid}" class="primary-btn">+ Add New Question</a>
      </div>

      <!-- No Questions -->
      <c:if test="${empty questionList}">
        <p class="no-data">No questions found. Click “Add New Question” to create one.</p>
      </c:if>

      <!-- Question List -->
      <c:forEach var="q" items="${questionList}">
        <div class="question-card">
          <h3>Q${q.queId}: ${q.questionText}</h3>
          <ul class="options-list">
            <li><strong>A)</strong> ${q.optionA}</li>
            <li><strong>B)</strong> ${q.optionB}</li>
            <li><strong>C)</strong> ${q.optionC}</li>
            <li><strong>D)</strong> ${q.optionD}</li>
          </ul>

          <p class="correct-answer">✅ Correct Answer: <strong>${q.correctOption}</strong></p>

          <div class="btn-container">
            <a href="editQuestion?quesId=${q.queId}&qid=${qid}" class="action-btn action-edit">Edit</a>
            <a href="deleteQuestion?quesId=${q.queId}&qid=${qid}" class="action-btn action-delete" 
              onclick="return confirm('Are you sure you want to delete this question?')">
              Delete
            </a>
          </div>
        </div>
      </c:forEach>

      <!-- Back Button -->
      <div class="back-btn-container">
        <a href="viewQuizzes" class="back-btn">← Back to Quizzes</a>
      </div>
    </div>
  </div>
</body>
</html>
