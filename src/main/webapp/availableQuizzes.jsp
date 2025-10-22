<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Quizzes</title>
<link rel="stylesheet" href="CSS/tablePages.css">

<script>
  // === Show custom confirm box ===
  function confirmStart(event, quizTitle, link) {
    event.preventDefault();

    // Remove any existing popup
    document.querySelectorAll('.confirm-box').forEach(el => el.remove());

    // Create popup container
    const confirmBox = document.createElement("div");
    confirmBox.className = "confirm-box";

    confirmBox.innerHTML = `
      <div class="confirm-content">
        <h3>Start Quiz?</h3>
        <p>Once you begin, the timer will start immediately.</p>
        <div class="confirm-actions">
          <button class="confirm-yes">Yes, Start</button>
          <button class="confirm-no">Cancel</button>
        </div>
      </div>
    `;

    document.body.appendChild(confirmBox);

    // Button listeners
    confirmBox.querySelector(".confirm-yes").addEventListener("click", () => {
      window.location.href = link;
    });

    confirmBox.querySelector(".confirm-no").addEventListener("click", () => {
      confirmBox.remove();
    });
  }
</script>
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1>Available Quizzes for Batch: ${batch}</h1>

      <c:if test="${not empty message}">
        <div class="success-msg">${message}</div>
      </c:if>

      <c:if test="${empty quizzes}">
        <p class="no-data">No quizzes available for you right now.</p>
      </c:if>

      <c:if test="${not empty quizzes}">
        <table class="styled-table">
          <thead>
            <tr>
              <th>Quiz Title</th>
              <th>Subject</th>
              <th>Created At</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="quiz" items="${quizzes}">
              <tr>
                <td>${quiz.title}</td>
                <td>${quiz.subject}</td>
                <td>${quiz.createdAt}</td>
                <td>
                  <a href="startQuiz?qid=${quiz.qid}" 
                     class="start-btn"
                     onclick="confirmStart(event, '${fn:escapeXml(quiz.title)}', this.href)">
                    Start Quiz
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>
    </div>

    <div class="back-btn-container">
      <a href="studentDashboard.jsp" class="back-btn">← Back to Dashboard</a>
    </div>
  </div>
</body>
</html>
