# 🎓 EduTestPortal  
### _Smart Online Quiz Management System — Built with JSP, Servlets & MySQL_

**EduTestPortal** is a full-stack web application designed for **educational institutions** to manage, conduct, and evaluate quizzes efficiently.  
It streamlines interaction between **students, teachers, and administrators** with a clean, secure, and user-friendly interface.  

---

## 🚀 Overview

EduTestPortal provides a **centralized platform** where teachers can create and assign quizzes, students can attempt them in real time with timer functionality, and admins can manage the entire ecosystem — all within a modern web interface.

The system ensures secure access, real-time tracking, result analytics, and role-based control, making it a complete digital testing solution for academic environments.

---

## 👥 User Roles & Features

### 👨‍🎓 **Student Module**
- View **available quizzes** assigned to their batch.  
- Attempt quizzes with a **live timer and auto-submit**.  
- **Session-based recovery:** refreshing the page doesn’t reset timer or answers.  
- View **past results**, including score, percentage, and performance feedback.  
- Manage their **profile** with options to edit or delete their account.  

---

### 👨‍🏫 **Teacher Module**
- Create new quizzes with custom **duration, subject, and batch assignments**.  
- Add, edit, or delete questions dynamically.  
- Manage visibility (Show/Hide) of quizzes.  
- View **student performances** quiz-wise with batch analytics.  
- Fully secure login with **bcrypt password hashing**.  

---

### 🧑‍💼 **Admin Module**
- Dashboard showing key statistics: total teachers, students, quizzes, and visible quizzes.  
- Manage **students**, **teachers**, and **quizzes** across the platform.  
- View **teacher-created quizzes** and toggle visibility globally.  
- Access **all quiz results** for review or moderation.  
- Role-based access ensures data and operations isolation.  

---

## 💡 Core Functionalities

- 🕒 **Real-Time Quiz Timer**  
  Timer continues even after refresh; auto-submits on timeout.  

- 💾 **Session Persistence**  
  Student answers and remaining time saved in session to prevent data loss.  

- 🔐 **Secure Authentication**  
  All passwords stored with **bcrypt hashing** (via `at.favre.lib.crypto.bcrypt`).  

- 📊 **Result Analytics**  
  Displays score, percentage, and motivational feedback based on performance.  

- 🧭 **Unified UI & Theme**  
  Modern purple-accented dashboard, responsive layouts, and consistent design across all roles.  

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-------------|
| Frontend | HTML5, CSS3, JSP, JSTL |
| Backend | Java Servlets (Jakarta EE) |
| Database | MySQL with JDBC |
| Security | BCrypt password hashing |
| Server | Apache Tomcat 10 |
| Architecture | MVC (Model-View-Controller) |

---

## 🎨 UI & Design Highlights
- Global **education-themed background** with a subtle dark overlay.  
- White, glassy content containers with smooth shadows and rounded corners.  
- **Responsive tables and forms** for all management pages.  
- Consistent color theme:  
  - Primary: `#6c63ff`  
  - Accent: `#5a4fff`  
  - Danger: `#ff4d4d`  
  - Success: `#43a047`  

---

## 🧩 System Architecture

**Model:** Java Beans (`Student`, `Teacher`, `Quiz`, `Question`, `Result`, `Admin`)  
**DAO Layer:** Handles all database logic with prepared statements.  
**Controller:** Servlets for handling HTTP requests, session management, and routing.  
**View:** JSP pages with JSTL for dynamic rendering and consistent headers via `header.jsp`.  

---

## ✅ Current Status
All modules — **Student**, **Teacher**, and **Admin** — are **fully functional**, styled, and tested.  
The portal is secure, stable, and production-ready, designed to demonstrate **enterprise-grade web app development skills** using Java EE.

---

## 💬 In Short
EduTestPortal bridges the gap between **manual test handling** and **automated online evaluation**, empowering teachers, engaging students, and simplifying admin oversight — all in one cohesive Java web application.  
