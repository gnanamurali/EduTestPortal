<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login | EduTestPortal</title>
<link rel="stylesheet" href="CSS/loginPages.css">
</head>
<body>

    <div class="login-container">
        <div class="login-card">

            <!-- Logo Section -->
            <div class="logo-section">
                <img src="images/edutestbackground1.png" alt="EduTestPortal Logo" class="logo">
                <h2>Admin Login</h2>
                <p>Access your EduTestPortal Admin Dashboard</p>
            </div>

            <!-- Login Form -->
            <form action="adminLogin" method="post" class="login-form">
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                </div>

                <button type="submit" class="login-btn admin-btn">Login</button>
            </form>

            <!-- Error Message -->
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <p style="color: #ff4d4d; margin-top: 10px;"><%= message %></p>
            <%
                }
            %>

            <div class="extra-links">
                <a href="adminRegister.jsp">Don’t have an account? Register here</a>
            </div>
        </div>
    </div>

</body>
</html>
