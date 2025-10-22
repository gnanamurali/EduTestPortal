<%@page import="com.EduTestPortal.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
<link rel="stylesheet" href="CSS/layout.css">
<link rel="stylesheet" href="CSS/student.css">
</head>
<body>

  <jsp:include page="/includes/header.jsp" />

  <div class="student-dashboard">

    <h1>Welcome, ${currentStudent.name}</h1>
    <p>Ready to test your knowledge today?</p>

    <div class="student-actions">
      <a href="availableQuizzes" class="action-btn">View Available Quizzes</a>
      <a href="viewResult" class="action-btn">View Your Results</a>
    </div>

  </div>

</body>
</html>
