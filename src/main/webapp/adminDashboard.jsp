<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="CSS/layout.css">
<link rel="stylesheet" href="CSS/admin.css">
</head>

<body>
  
  <jsp:include page="/includes/header.jsp" />

  
  <div class="dashboard-wrapper">

   
    <section class="welcome-section">
      <h1>Welcome, ${currentAdmin.name}</h1>
      <p>Here’s your quick overview for today</p>
    </section>

  
 <!-- Scrolling Overview (animated stats) -->
<section class="overview-scroll">
  <div class="overview-track">
    <span>Total Students: ${totalStudents}</span>
    <span>Total Teachers: ${totalTeachers}</span>
    <span>Total Quizzes: ${totalQuizzes}</span>
    <span>Visible Quizzes: ${visibleQuizzes}</span>
  </div>
</section>



    <!-- Management Links -->
    <section class="management-links">
      <h2>Management</h2>
      <a href="ManageStudents" class="manage-btn">Manage Students</a>
      <a href="ManageTeachers" class="manage-btn">Manage Teachers</a>
      <a href="ManageQuizzes" class="manage-btn">Manage Quizzes</a>
    </section>

  </div>
</body>
</html>
