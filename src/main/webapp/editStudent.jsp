<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Student</title>
    <link rel="stylesheet" href="CSS/formPages.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <c:if test="${empty sessionScope.currentAdmin}">
        <c:redirect url="admin_login.jsp"/>
    </c:if>

    <div class="page-container">
        <div class="content-card">
            <h2 class="page-title">Edit Student Details</h2>

            <form action="UpdateStudent" method="post" class="styled-form">
                <input type="hidden" name="sid" value="${student.sid}" />

                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" name="name" value="${student.name}" required>
                </div>

                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" value="${student.email}" required>
                </div>

                <div class="form-group">
                    <label>Phone:</label>
                    <input type="text" name="phone" value="${student.phone}" required>
                </div>

                <div class="form-group">
                    <label>Department:</label>
                    <input type="text" name="department" value="${student.department}" required>
                </div>

                <div class="form-group">
                    <label>Year of Study:</label>
                    <input type="number" name="yearOfStudy" value="${student.yearOfStudy}" required>
                </div>

                <div class="form-group">
                    <label>Batch:</label>
                    <select name="batch" required>
                        <option value="B1" ${student.batch == 'B1' ? 'selected' : ''}>B1</option>
                        <option value="B2" ${student.batch == 'B2' ? 'selected' : ''}>B2</option>
                        <option value="B3" ${student.batch == 'B3' ? 'selected' : ''}>B3</option>
                    </select>
                </div>

                <div class="btn-container">
                    <button type="submit" class="primary-btn">Update Student</button>
                    <a href="ManageStudents" class="secondary-btn">Cancel</a>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
