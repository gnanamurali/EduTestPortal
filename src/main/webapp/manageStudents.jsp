<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Students</title>
    <link rel="stylesheet" href="CSS/tablePages.css">
</head>
<body>
    <jsp:include page="/includes/header.jsp" />

    <div class="page-container">
        <div class="content-card">
            <h1>Manage Students</h1>

            <c:if test="${not empty param.msg}">
                <div class="success-msg">${param.msg}</div>
            </c:if>

            <form method="get" action="ManageStudents" style="margin-bottom: 20px;">
                <label for="batch" style="font-weight: 500;">Filter by Batch:</label>
                <select name="batch" id="batch" onchange="this.form.submit()"
                        style="padding: 6px 10px; border-radius: 6px; border: 1px solid #ccc;">
                    <option value="ALL" ${selectedBatch == 'ALL' ? 'selected' : ''}>All</option>
                    <option value="B1" ${selectedBatch == 'B1' ? 'selected' : ''}>Batch B1</option>
                    <option value="B2" ${selectedBatch == 'B2' ? 'selected' : ''}>Batch B2</option>
                    <option value="B3" ${selectedBatch == 'B3' ? 'selected' : ''}>Batch B3</option>
                </select>
            </form>

            <c:choose>
                <c:when test="${not empty studentList}">
                    <table class="styled-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Department</th>
                                <th>Year</th>
                                <th>Batch</th>
                                <th>Actions</th>
                                <th>Results</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${studentList}">
                                <tr>
                                    <td>${s.sid}</td>
                                    <td>${s.name}</td>
                                    <td>${s.email}</td>
                                    <td>${s.phone}</td>
                                    <td>${s.department}</td>
                                    <td>${s.yearOfStudy}</td>
                                    <td>${s.batch}</td>
                                    <td>
                                        <a href="EditStudent?sid=${s.sid}" class="btn edit-btn">Edit</a>
                                        <a href="DeleteStudent?sid=${s.sid}"
                                           class="btn delete-btn"
                                           onclick="return confirm('Are you sure you want to delete this student?');">
                                           Delete
                                        </a>
                                    </td>
                                    <td>
                                        <a href="ViewStudentResults?sid=${s.sid}" class="btn view-btn">View Results</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="no-data">No students found for the selected batch.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="back-btn-container">
            <a href="adminDashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
