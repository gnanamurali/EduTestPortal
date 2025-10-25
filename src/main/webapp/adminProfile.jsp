<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Profile</title>
<link rel="stylesheet" href="CSS/formPages.css">
<link rel="stylesheet" href="CSS/profile.css">
</head>
<body>

  <jsp:include page="/includes/header.jsp" />

  <div class="profile-container">
      <img src="${pageContext.request.contextPath}/images/profile.png" alt="Profile Picture">
      <h2>${admin.name}</h2>
      <div class="profile-details">
          <p><strong>Email:</strong> ${admin.email}</p>
          <p><strong>Registered On:</strong> ${admin.registeredAt}</p>
      </div>

      <div class="btn-container">
          <a href="editAdminByAdmin" class="btn edit-btn">Edit Profile</a>
      </div>
  </div>

</body>
</html>
