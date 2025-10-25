<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Teacher Registration | EduTestPortal</title>
<link rel="stylesheet" href="CSS/registerPages.css">
</head>
<body>

    <div class="register-container">
        <div class="register-card">

            <!-- Logo and Header -->
            <div class="logo-section">
                <img src="images/edutestbackground1.png" alt="EduTestPortal Logo" class="logo">
                <h2>Teacher Registration</h2>
                <p>Create your EduTestPortal teacher account</p>
            </div>

            <!-- Registration Form -->
            <form action="registerTeacher" method="post" class="register-form">

                <div class="form-group">
                    <label for="userName">Full Name</label>
                    <input type="text" id="userName" name="userName" placeholder="Enter your full name" required>
                </div>

                <div class="form-group">
                    <label for="userEmail">Email Address</label>
                    <input type="email" id="userEmail" name="userEmail" placeholder="Enter your email" required>
                </div>

                <div class="form-group">
                    <label for="userPhone">Phone Number</label>
                    <input type="text" id="userPhone" name="userPhone" placeholder="Enter your phone number">
                </div>

                <div class="form-group">
                    <label for="userPass">Create Password</label>
                    <input type="password" id="userPass" name="userPass" placeholder="Enter your password" required>
                </div>

                <div class="form-group">
                    <label for="userSub">Subject</label>
                    <input type="text" id="userSub" name="userSub" placeholder="Enter your subject">
                </div>

                <button type="submit" class="register-btn teacher-btn">Create Account</button>
            </form>

            <div class="extra-links">
                <a href="teacherLogin.jsp">Already have an account? Login</a>
            </div>

        </div>
    </div>

</body>
</html>
