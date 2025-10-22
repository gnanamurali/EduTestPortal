<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Teachers</title>
    <link rel="stylesheet" href="CSS/tablePages.css">
</head>
<body>
    <jsp:include page="/includes/header.jsp" />

    <div class="page-container">
        <div class="content-card">
            <h1>Manage Teachers</h1>

            <c:if test="${not empty param.msg}">
                <div class="success-msg">${param.msg}</div>
            </c:if>

            <c:choose>
                <c:when test="${not empty teacherList}">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Subject</th>
                                <th>Actions</th>
                                <th>Quizzes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="t" items="${teacherList}">
                                <tr>
                                    <td>${t.tid}</td>
                                    <td>${t.name}</td>
                                    <td>${t.email}</td>
                                    <td>${t.phone}</td>
                                    <td>${t.subject}</td>
                                    <td>
                                        <a href="EditTeacher?tid=${t.tid}" class="btn edit-btn">Edit</a>
                                        <a href="DeleteTeacher?tid=${t.tid}" 
                                           class="btn delete-btn"
                                           onclick="return confirm('Are you sure you want to delete this teacher?');">
                                           Delete
                                        </a>
                                    </td>
                                    <td>
                                        <a href="ViewTeacherQuizzes?tid=${t.tid}" class="btn view-btn">View Quizzes</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <p class="no-data">No teachers found.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="back-btn-container">
            <a href="adminDashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>