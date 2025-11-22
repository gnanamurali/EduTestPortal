<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Login | EduTestPortal</title>
<link rel="stylesheet" href="CSS/loginPages.css">
</head>
<body>

    <div class="login-container">
        <div class="login-card">

            <!-- Logo -->
            <div class="logo-section">
                <img src="images/edutestbackground1.png" alt="EduTestPortal Logo" class="logo">
                <h2>Student Login</h2>
                <p>Sign in to your EduTestPortal account</p>
            </div>

            <!-- Login Form -->
            <form action="loginServlet" method="post" class="login-form">
                <div class="form-group">
                    <label for="userEmail">Email Address</label>
                    <input type="email" id="userEmail" name="userEmail" placeholder="Enter your email" required>
                </div>

                <div class="form-group">
                    <label for="userPass">Password</label>
                    <input type="password" id="userPass" name="userPass" placeholder="Enter your password" required>
                </div>

                <button type="submit" class="login-btn">Login</button>
            </form>

            <div class="extra-links">
                <a href="register.jsp">New here? Create your account</a>
            </div>
        </div>
    </div>

</body>
</html>
