<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Teacher Profile</title>
<link rel="stylesheet" href="CSS/formPages.css">
<link rel="stylesheet" href="CSS/profile.css">
</head>
<body>

  <jsp:include page="/includes/header.jsp" />

  <div class="profile-container edit-container">
      <h2>Edit Your Profile</h2>

      <form action="updateTeacherByTeacher" method="post" class="edit-form">
          <div class="form-row">
              <div class="form-group">
                  <label>Name</label>
                  <input type="text" name="name" value="${teacher.name}" required>
              </div>
              <div class="form-group">
                  <label>Email</label>
                  <input type="email" name="email" value="${teacher.email}" required>
              </div>
          </div>

          <div class="form-row">
              <div class="form-group">
                  <label>Phone</label>
                  <input type="text" name="phone" value="${teacher.phone}" required>
              </div>
              <div class="form-group">
                  <label>Subject</label>
                  <input type="text" name="subject" value="${teacher.subject}" required>
              </div>
          </div>

          <div class="btn-container center-btns">
              <button type="submit" class="btn edit-btn">Save Changes</button>
              <a href="teacherprofile" class="btn delete-btn">Cancel</a>
          </div>
      </form>
  </div>

</body>
</html>
