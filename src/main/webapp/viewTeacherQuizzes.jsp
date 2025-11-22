<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Quizzes</title>
    <link rel="stylesheet" href="CSS/tablePages.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <div class="page-container">
        <div class="content-card">
            <h1>Quizzes Created by Teacher ID: T${tid}</h1>

            <c:choose>
                <c:when test="${not empty quizList}">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>Quiz ID</th>
                                <th>Title</th>
                                <th>Subject</th>
                                <th>Created At</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="q" items="${quizList}">
                                <tr>
                                    <td>Q${q.qid}</td>
                                    <td>${q.title}</td>
                                    <td>${q.subject}</td>
                                    <td>${q.createdAt}</td>
                                    <td>
                                        <a href="quizResults?qid=${q.qid}" class="result-btn">View Results</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <p class="no-data">No quizzes found for this teacher.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="back-btn-container">
            <a href="ManageTeachers" class="back-btn">← Back to Manage Teachers</a>
        </div>
    </div>

</body>
</html>
