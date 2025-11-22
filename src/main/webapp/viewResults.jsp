<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Your Quiz Results</title>
  <link rel="stylesheet" href="CSS/tablePages.css">
  <style>
    /* Extra highlight row colors based on percentage */
    .row-excellent { background-color: #e9f7ef !important; } /* light green */
    .row-good { background-color: #eaf3ff !important; }     /* light blue */
    .row-average { background-color: #fff9e6 !important; }  /* light yellow */
    .row-bad { background-color: #ffeaea !important; }      /* light red */

    /* Make the percentage text bold */
    .percent-cell {
      font-weight: 600;
    }
  </style>
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1 class="page-title">Quiz Results for ${studentName}</h1>

      <p><strong>Batch:</strong> ${studentBatch}</p>
      <p><strong>Total Quizzes Attempted:</strong> ${results.size()}</p>

      <c:if test="${empty results}">
        <p class="no-data">You haven’t attempted any quizzes yet.</p>
      </c:if>

      <c:if test="${not empty results}">
        <table class="styled-table">
          <thead>
            <tr>
              <th>Quiz Title</th>
              <th>Subject</th>
              <th>Score</th>
              <th>Percentage</th>
              <th>Taken At</th>
              <th>Performance</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="res" items="${results}">
              <c:set var="rowClass" value="" />
              <c:choose>
                <c:when test="${res.percentage >= 90}">
                  <c:set var="rowClass" value="row-excellent" />
                </c:when>
                <c:when test="${res.percentage >= 75}">
                  <c:set var="rowClass" value="row-good" />
                </c:when>
                <c:when test="${res.percentage >= 50}">
                  <c:set var="rowClass" value="row-average" />
                </c:when>
                <c:otherwise>
                  <c:set var="rowClass" value="row-bad" />
                </c:otherwise>
              </c:choose>

              <tr class="${rowClass}">
                <td>${res.quizTitle}</td>
                <td>${res.quizSubject}</td>
                <td>${res.score}</td>
                <td class="percent-cell">${String.format("%.2f", res.percentage)}%</td>
                <td>${res.takenAt}</td>
                <td>
                  <c:choose>
                    <c:when test="${res.percentage >= 90}">
                      <span class="badge badge-excellent">Excellent</span>
                    </c:when>
                    <c:when test="${res.percentage >= 75}">
                      <span class="badge badge-good">Good</span>
                    </c:when>
                    <c:when test="${res.percentage >= 50}">
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

    <div class="back-btn-row">
      <a href="availableQuizzes" class="back-btn">Attempt More Quizzes</a>
      <a href="studentDashboard.jsp" class="back-btn">Back to Dashboard</a>
    </div>
  </div>
</body>
</html>
