<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quiz Results</title>
<link rel="stylesheet" href="CSS/tablePages.css">
<style>
  /* Row highlights based on percentage */
  .row-excellent { background-color: #e9f7ef !important; } /* light green */
  .row-good { background-color: #eaf3ff !important; }     /* light blue */
  .row-average { background-color: #fff9e6 !important; }  /* light yellow */
  .row-bad { background-color: #ffeaea !important; }      /* light red */
  
  .percent-cell { font-weight: 600; }
</style>
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1 class="page-title">Results for Quiz: ${quiz.title}</h1>

      <!-- No Attempts -->
      <c:if test="${empty resultsList}">
        <p class="no-data">No students have attempted this quiz yet.</p>
      </c:if>

      <!-- Quiz Summary -->
      <c:if test="${not empty quizStats}">
        <h2 class="section-title">Quiz Performance Summary</h2>
        <table class="styled-table">
          <thead>
            <tr>
              <th>Total Attempts</th>
              <th>Highest Score</th>
              <th>Lowest Score</th>
              <th>Average Score</th>
              <th>Average %</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${quizStats.totalAttempts}</td>
              <td>${quizStats.highestScore}</td>
              <td>${quizStats.lowestScore}</td>
              <td>${quizStats.averageScore}</td>
              <td>${String.format("%.2f", quizStats.averagePercentage)}%</td>
            </tr>
          </tbody>
        </table>
      </c:if>

      <!-- Individual Results -->
      <c:if test="${not empty resultsList}">
        <h2 class="section-title">Individual Student Results</h2>
        <table class="styled-table">
          <thead>
            <tr>
              <th>Student ID</th>
              <th>Name</th>
              <th>Batch</th>
              <th>Score</th>
              <th>Percentage</th>
              <th>Taken At</th>
              <th>Performance</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="r" items="${resultsList}">
              <c:set var="rowClass" value="" />
              <c:choose>
                <c:when test="${r.percentage >= 90}">
                  <c:set var="rowClass" value="row-excellent" />
                </c:when>
                <c:when test="${r.percentage >= 75}">
                  <c:set var="rowClass" value="row-good" />
                </c:when>
                <c:when test="${r.percentage >= 50}">
                  <c:set var="rowClass" value="row-average" />
                </c:when>
                <c:otherwise>
                  <c:set var="rowClass" value="row-bad" />
                </c:otherwise>
              </c:choose>

              <tr class="${rowClass}">
                <td>${r.sid}</td>
                <td>${r.studentName}</td>
                <td>${r.studentBatch}</td>
                <td>${r.score}</td>
                <td class="percent-cell">${String.format("%.2f", r.percentage)}%</td>
                <td>${r.takenAt}</td>
                <td>
                  <c:choose>
                    <c:when test="${r.percentage >= 90}">
                      <span class="badge badge-excellent">Excellent</span>
                    </c:when>
                    <c:when test="${r.percentage >= 75}">
                      <span class="badge badge-good">Good</span>
                    </c:when>
                    <c:when test="${r.percentage >= 50}">
                      <span class="badge badge-warn">Needs Improvement</span>
                    </c:when>
                    <c:otherwise>
                      <span class="badge badge-bad">Try Again</span>
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>
    </div>

    <!-- Button Group -->
    <div class="button-group">
      <c:choose>
        <c:when test="${userRole == 'admin'}">
          <a href="ManageQuizzes" class="secondary-btn">Back to Your Quizzes</a>
          <a href="adminDashboard" class="secondary-btn">Back to Dashboard</a>
        </c:when>
        <c:otherwise>
          <a href="teacherViewResult" class="secondary-btn">← Back to Your Quizzes</a>
          <a href="teacherDashboard.jsp" class="secondary-btn">Back to Dashboard</a>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</body>
</html>
