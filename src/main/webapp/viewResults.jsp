<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Quiz Results</title>
</head>
<body>

<h1>Quiz Results for ${studentName}</h1>
<p>Batch: <strong>${studentBatch}</strong></p>
<p>Total Quizzes Attempted: <strong>${results.size()}</strong></p>
<hr>

<c:if test="${empty results}">
  <p>You haven’t attempted any quizzes yet.</p>
</c:if>

<c:if test="${not empty results}">
  <table border="1" cellpadding="8" cellspacing="0">
    <tr>
      <th>Quiz Title</th>
      <th>Subject</th>
      <th>Score</th>
      <th>Taken At</th>
      <th>Performance</th>
    </tr>

    <c:forEach var="res" items="${results}">
      <tr>
        <td>${res.quizTitle}</td>
        <td>${res.quizSubject}</td>
        <td>${res.score}</td>
        <td>${res.takenAt}</td>
        <td>
          <c:choose>
            <c:when test="${res.score >= 9}">Excellent 💪</c:when>
            <c:when test="${res.score >= 7}">Good 👍</c:when>
            <c:when test="${res.score >= 5}">Needs Improvement 🟡</c:when>
            <c:otherwise>Try Again 🔴</c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<br>
<a href="availableQuizzes">Attempt More Quizzes</a> |
<a href="studentDashboard.jsp">Back to Dashboard</a>

</body>
</html>
