<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("currentTeacher") == null) {
        response.sendRedirect("teacherLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Quiz | ${currentTeacher.name}</title>
<link rel="stylesheet" href="CSS/formPages.css">
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1 class="page-title">New Quiz by ${currentTeacher.name}</h1>

      <c:if test="${not empty message}">
        <div class="success-msg">${message}</div>
      </c:if>

      <form action="createQuiz" method="post" class="styled-form">
        <div class="form-group">
          <label for="quizSub">Quiz Subject</label>
          <input type="text" name="quizSubject" value="${currentTeacher.subject}" readonly>
        </div>

        <div class="form-group">
          <label for="quizTitle">Quiz Title</label>
          <input type="text" id="quizTitle" name="quizTitle" placeholder="Enter quiz title" required>
        </div>

        <div class="form-group">
          <label for="quizDuration">Duration (in minutes)</label>
          <input type="number" id="quizDuration" name="quizDuration" min="1" value="10" required>
        </div>

        <div class="form-group">
          <label>Assign to Batches</label>
          <div class="checkbox-group">
            <label><input type="checkbox" name="batch" value="B1"> B1</label>
            <label><input type="checkbox" name="batch" value="B2"> B2</label>
            <label><input type="checkbox" name="batch" value="B3"> B3</label>
          </div>
        </div>

        <div class="btn-container">
          <input type="submit" value="Create Quiz" class="primary-btn">
          <a href="teacherDashboard.jsp" class="secondary-btn">Cancel</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
