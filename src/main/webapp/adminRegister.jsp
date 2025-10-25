<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Registration | EduTestPortal</title>
<link rel="stylesheet" href="CSS/registerPages.css">
</head>
<body>

    <div class="register-container">
        <div class="register-card">

            <!-- Logo and Header -->
            <div class="logo-section">
                <img src="<%= request.getContextPath() %>/images/edutestbackground1.png" alt="EduTestPortal Logo" class="logo">
                <h2>Admin Registration</h2>
                <p>Create your EduTestPortal admin account</p>
            </div>

            <!-- Registration Form -->
            <form action="adminRegister" method="post" class="register-form">

                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" id="name" name="name" placeholder="Enter your full name" required>
                </div>

                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>

                <div class="form-group">
                    <label for="password">Create Password</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                </div>

                <button type="submit" class="register-btn admin-btn">Register Admin</button>
            </form>

            <!-- Error Message -->
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <p style="color:#ff4d4d; margin-top:10px;"><%= message %></p>
            <%
                }
            %>

            <div class="extra-links">
                <a href="admin_login.jsp">Already have an account? Login here</a>
            </div>

        </div>
    </div>

</body>
</html>
