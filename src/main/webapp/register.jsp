<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration | EduTestPortal</title>
<link rel="stylesheet" href="CSS/registerPages.css">
</head>
<body>

    <div class="register-container">
        <div class="register-card">

            <!-- Logo and Header -->
            <div class="logo-section">
                <img src="<%= request.getContextPath() %>/images/edutestbackground1.png" alt="EduTestPortal Logo" class="logo">
                <h2>Student Registration</h2>
                <p>Create your EduTestPortal student account</p>
            </div>

            <!-- Registration Form -->
            <form action="registerServlet" method="post" class="register-form">

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
                    <label for="userDept">Department</label>
                    <input type="text" id="userDept" name="userDept" placeholder="Enter your department">
                </div>

                <div class="form-group">
                    <label for="userYos">Year of Study</label>
                    <input type="number" id="userYos" name="userYos" placeholder="Enter your year of study">
                </div>

                <div class="form-group">
                    <label for="userBatch">Batch</label>
                    <select id="userBatch" name="userBatch" required>
                        <option value="">Select Batch</option>
                        <option value="B1">BATCH-1</option>
                        <option value="B2">BATCH-2</option>
                        <option value="B3">BATCH-3</option>
                    </select>
                </div>

                <button type="submit" class="register-btn">Create Account</button>
            </form>

            <div class="extra-links">
                <a href="login.jsp">Already have an account? Login</a>
            </div>

        </div>
    </div>

</body>
</html>
