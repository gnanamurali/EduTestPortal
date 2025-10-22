<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Quiz Results</title>
    <link rel="stylesheet" href="CSS/tablePages.css">
    <style>
        /* Row colors based on percentage */
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
            <h1>Quiz Results for Student ID: S${sid}</h1>

            <c:choose>
                <c:when test="${not empty results}">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>Quiz ID</th>
                                <th>Quiz Title</th>
                                <th>Subject</th>
                                <th>Score</th>
                                <th>Percentage</th>
                                <th>Taken At</th>
                                <th>Performance</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="r" items="${results}">
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
                                    <td>Q${r.qid}</td>
                                    <td>${r.quizTitle}</td>
                                    <td>${r.quizSubject}</td>
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
                </c:when>

                <c:otherwise>
                    <p class="no-data">No results found for this student.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="back-btn-container">
            <a href="ManageStudents" class="back-btn">← Back to Manage Students</a>
        </div>
    </div>

</body>
</html>
