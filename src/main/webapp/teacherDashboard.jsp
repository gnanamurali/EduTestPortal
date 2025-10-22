<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher Dashboard</title>
<link rel="stylesheet" href="CSS/layout.css">
<link rel="stylesheet" href="CSS/teacher.css">
</head>
<body>
  
  <jsp:include page="/includes/header.jsp" />

  <div class="teacher-dashboard">
    <h1>Welcome, ${currentTeacher.name}</h1>
    <p>Manage your quizzes and monitor student results below.</p>

    <div class="teacher-actions">
      <a href="createQuiz.jsp" class="action-btn">Create New Quiz</a>
      <a href="viewQuizzes" class="action-btn">View Your Quizzes</a>
      <a href="teacherViewResult" class="action-btn">View Results</a>
    </div>
  </div>

</body>
</html>
