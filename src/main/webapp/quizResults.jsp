<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Results</title>
</head>
<body>

<h1>Results for Quiz: ${quizTitle}</h1>

<c:if test="${empty resultsList}">
    <p>No students have attempted this quiz yet.</p>
</c:if>

<h2> Quiz Performance Summary</h2>
<c:if test="${not empty quizStats}">
  <table border="1" cellpadding="8" cellspacing="0">
    <tr>
      <th>Total Attempts</th>
      <th>Highest Score</th>
      <th>Lowest Score</th>
      <th>Average Score</th>
    </tr>
    <tr>
      <td>${quizStats.totalAttempts}</td>
      <td>${quizStats.highestScore}</td>
      <td>${quizStats.lowestScore}</td>
      <td>${quizStats.averageScore}</td>
    </tr>
  </table>
</c:if>
<hr>

<c:if test="${not empty resultsList}">
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Student ID</th>
            <th>Name</th>
            <th>Batch</th>
            <th>Score</th>
            <th>Taken At</th>
        </tr>

        <c:forEach var="r" items="${resultsList}">
            <tr>
                <td>${r.sid}</td>
                <td>${r.studentName}</td>
                <td>${r.studentBatch}</td>
                <td>${r.score}</td>
                <td>${r.takenAt}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<br><br>
<a href="teacherViewResult">Back to Quizzes</a>
<a href="teacherDashboard.jsp">Back to Dashboard</a>

</body>
</html>
