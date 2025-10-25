<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- HEADER v7 -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/layout.css">

<nav class="navbar">
  <div class="logo">
  <img src="${pageContext.request.contextPath}/images/edutestbackground1.png" alt="EduTestPortal">
</div>


  <ul class="nav-links">
    <c:choose>
      <c:when test="${not empty sessionScope.currentAdmin}">
        <li><a href="adminDashboard.jsp">Dashboard</a></li>
        <li><a href="ManageQuizzes">Manage Quizzes</a></li>
        <li><a href="adminprofile">Account</a></li>
      </c:when>

      <c:when test="${not empty sessionScope.currentTeacher}">
        <li><a href="teacherDashboard.jsp">Dashboard</a></li>
        <li><a href="viewQuizzes">Manage Quizzes</a></li>
        <li><a href="teacherprofile">Account</a></li>
      </c:when>

      <c:when test="${not empty sessionScope.currentStudent}">
        <li><a href="studentDashboard.jsp">Dashboard</a></li>
        <li><a href="availableQuizzes">Quizzes</a></li>
        <li><a href="studentprofile">Account</a></li>
      </c:when>

      <c:otherwise>
        <li><a href="login.jsp">Login</a></li>
      </c:otherwise>
    </c:choose>

    <li><a href="logout">Logout</a></li>
  </ul>
</nav>
