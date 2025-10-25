<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
<link rel="stylesheet" href="CSS/formPages.css">
<link rel="stylesheet" href="CSS/profile.css">
</head>
<body>

  <jsp:include page="/includes/header.jsp" />

  <div class="profile-container edit-container">
      <h2>Edit Your Profile</h2>

      <form action="updateStudentByStudent" method="post" class="edit-form">

          <div class="form-row">
              <div class="form-group">
                  <label>Name</label>
                  <input type="text" name="name" value="${student.name}" required>
              </div>
              <div class="form-group">
                  <label>Email</label>
                  <input type="email" name="email" value="${student.email}" required>
              </div>
          </div>

          <div class="form-row">
              <div class="form-group">
                  <label>Phone</label>
                  <input type="text" name="phone" value="${student.phone}" required>
              </div>
              <div class="form-group">
                  <label>Department</label>
                  <input type="text" name="department" value="${student.department}" required>
              </div>
          </div>

          <div class="form-row">
              <div class="form-group">
                  <label>Year of Study</label>
                  <input type="number" name="yearOfStudy" value="${student.yearOfStudy}" min="1" max="4" required>
              </div>
              <div class="form-group">
                  <label>Batch</label>
                  <input type="text" value="${student.batch}" readonly>
              </div>
          </div>

          <div class="btn-container center-btns">
              <button type="submit" class="btn edit-btn">Save Changes</button>
              <a href="studentprofile" class="btn delete-btn">Cancel</a>
          </div>
      </form>
  </div>

</body>
</html>
