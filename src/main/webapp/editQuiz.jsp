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
<title>Edit Quiz | ${quiz.title}</title>
<link rel="stylesheet" href="CSS/formPages.css">
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1 class="page-title">Editing Quiz: ${quiz.title}</h1>

      <form action="updateQuiz" method="post" class="styled-form">
        <input type="hidden" name="qid" value="${quiz.qid}">

        <div class="form-group">
          <label for="quizTitle">Quiz Title</label>
          <input type="text" id="quizTitle" name="quizTitle" value="${quiz.title}" required>
        </div>

        <div class="form-group">
          <label for="quizSub">Quiz Subject</label>
          <input type="text" id="quizSub" name="quizSubject" value="${quiz.subject}" required>
        </div>

        <div class="form-group">
          <label for="quizDur">Quiz Duration (in minutes)</label>
          <input type="number" id="quizDur" name="duration" value="${quiz.duration}" min="1" required>
        </div>

        <div class="btn-container">
          <input type="submit" value="Update Quiz" class="primary-btn">
          <a href="viewQuizzes" class="secondary-btn">Cancel</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
