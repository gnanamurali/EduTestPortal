<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Admin Profile</title>
<link rel="stylesheet" href="CSS/formPages.css">
<link rel="stylesheet" href="CSS/profile.css">
</head>
<body>

  <jsp:include page="/includes/header.jsp" />

  <div class="profile-container edit-container">
      <h2>Edit Your Profile</h2>

      <form action="updateAdminByAdmin" method="post" class="edit-form">
          <div class="form-row">
              <div class="form-group">
                  <label>Name</label>
                  <input type="text" name="name" value="${admin.name}" required>
              </div>
              <div class="form-group">
                  <label>Email</label>
                  <input type="email" name="email" value="${admin.email}" required>
              </div>
          </div>

          <div class="btn-container center-btns">
              <button type="submit" class="btn edit-btn">Save Changes</button>
              <a href="adminprofile" class="btn delete-btn">Cancel</a>
          </div>
      </form>
  </div>

</body>
</html>
