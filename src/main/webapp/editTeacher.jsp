<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Teacher</title>
    <link rel="stylesheet" href="CSS/formPages.css">
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <c:if test="${empty sessionScope.currentAdmin}">
        <c:redirect url="admin_login.jsp"/>
    </c:if>

    <div class="page-container">
        <div class="content-card">
            <h2 class="page-title">Edit Teacher Details</h2>

            <form action="UpdateTeacher" method="post" class="styled-form">
                <input type="hidden" name="tid" value="${teacher.tid}" />

                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" name="name" value="${teacher.name}" required>
                </div>

                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" value="${teacher.email}" required>
                </div>

                <div class="form-group">
                    <label>Phone:</label>
                    <input type="text" name="phone" value="${teacher.phone}" required>
                </div>

                <div class="form-group">
                    <label>Subject:</label>
                    <input type="text" name="subject" value="${teacher.subject}" required>
                </div>

                <div class="btn-container">
                    <button type="submit" class="primary-btn">Update Teacher</button>
                    <a href="ManageTeachers" class="secondary-btn">Cancel</a>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
