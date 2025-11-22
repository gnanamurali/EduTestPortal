<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Profile</title>
<link rel="stylesheet" href="CSS/formPages.css">
<link rel="stylesheet" href="CSS/profile.css">
</head>
<body>

  <jsp:include page="/includes/header.jsp" />

  <div class="profile-container">
      <img src="${pageContext.request.contextPath}/images/profile.png" alt="Profile Picture">
      <h2>${student.name}</h2>
      <div class="profile-details">
          <p><strong>Email:</strong> ${student.email}</p>
          <p><strong>Phone:</strong> ${student.phone}</p>
          <p><strong>Department:</strong> ${student.department}</p>
          <p><strong>Year of Study:</strong> ${student.yearOfStudy}</p>
          <p><strong>Batch:</strong> ${student.batch}</p>
          <p><strong>Registered On:</strong> ${student.registeredAt}</p>
      </div>

      <div class="btn-container">
          <a href="editStudentByStudent" class="btn edit-btn">Edit Profile</a>
          <form action="deletestudent" method="post"
                onsubmit="return confirm('Are you sure you want to delete your account?')"
                style="display:inline;">
              <button type="submit" class="btn delete-btn">Delete Account</button>
          </form>
      </div>
  </div>

</body>
</html>
