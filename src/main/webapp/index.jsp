<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>EduTestPortal | Home</title>
  <link rel="stylesheet" href="CSS/homePage.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>

  <!-- 🧭 NAVBAR -->
  <header>
    <div class="nav-container">
      <div class="logo-section">
        <img src="images/edutestbackground1.png" alt="EduTestPortal Logo" class="logo-img">
      </div>
      <nav>
        <ul>
          <li><a href="login.jsp" class="btn login-btn">Login</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </nav>
    </div>
  </header>

  <!-- 💡 HERO SECTION -->
  <section class="hero">
    <div class="overlay"></div>
    <div class="hero-content">
      <h1>Empowering Smarter Learning and Teaching.</h1>
      <p>A unified platform for teachers to create quizzes and for students to test their knowledge.</p>
      <div class="hero-buttons">
        <a href="login.jsp" class="btn primary">Login to Continue</a>
        <a href="register.jsp" class="btn secondary">Register Now</a>
      </div>
    </div>
  </section>

  <!-- 📘 ABOUT SECTION -->
  <section id="about" class="about">
    <h2>About EduTestPortal</h2>
    <p>
      EduTestPortal is a secure, interactive platform designed for teachers and students to manage, take, and evaluate quizzes online.
      With automated grading, result analysis, and real-time access, learning has never been easier.
    </p>

    <div class="features">
      <div class="card">
        <h3>🧠 For Students</h3>
        <p>Attempt quizzes and track your results in real-time.</p>
      </div>
      <div class="card">
        <h3>👨‍🏫 For Teachers</h3>
        <p>Create, assign, and manage quizzes by batch with ease.</p>
      </div>
      <div class="card">
        <h3>🧾 Real-Time Results</h3>
        <p>Get instant feedback and performance analytics after every quiz.</p>
      </div>
    </div>
  </section>

  <!-- 💪 WHY CHOOSE US -->
  <section class="why">
    <h2>Why Choose Us?</h2>
    <div class="why-grid">
      <div class="why-card">
        <h3>🔒 Secure & Private</h3>
        <p>Built with bcrypt encryption and session-based access control.</p>
      </div>
      <div class="why-card">
        <h3>⚡ Real-Time Evaluation</h3>
        <p>Automatic quiz correction and percentage calculation instantly.</p>
      </div>
      <div class="why-card">
        <h3>✨ Simple Interface</h3>
        <p>Minimalist design that’s easy for both teachers and students.</p>
      </div>
    </div>
  </section>

  <!-- 📞 CONTACT -->
  <section id="contact" class="contact">
    <h2>Contact Us</h2>
    <p>Email: support@edutestportal.com</p>
  </section>

  <!-- 🧱 FOOTER -->
  <footer>
    <p>© 2025 EduTestPortal | Designed by Gnana Murali</p>
    <div class="footer-links">
      <a href="teacherLogin.jsp">Teacher Login</a> |
      <a href="#">Terms</a> |
      <a href="#">Privacy</a> |
      <a href="#contact">Contact</a>
    </div>
  </footer>

</body>
</html>
