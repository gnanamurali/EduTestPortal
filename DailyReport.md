# **DAILY REPORT**



##### **Day 1 (16-09-25)**



**Project Setup**:



Created new Dynamic Web Project in Eclipse (EduTestPortal).



Chose Dynamic Web Module 5.0 (left as default).



**MVC Planning:**



Decided on 3 packages:



model → POJOs (Student, Teacher, Quiz, Admin, Question, Result).



db → DBConnection + DAO classes.



controllers → Servlets.



**First Run:**



Tested a simple HelloServlet and Hello.jsp to confirm environment works.



##### **Day 2 (17-09-25)**



Database Finalization



Cleaned up DB schema:



Removed unwanted password column in quizzes.



Added phone field in students.



Added quiz\_batches table to assign quizzes to multiple batches.



Model Layer (POJOs)



Implemented all 6 classes in com.EduTestPortal.model:



Student,Teacher,Quiz,Admin,Question,Result



Each class has fields + getters/setters mapped to DB columns.



**DB Layer Setup**



Created DBConnection.java in com.EduTestPortal.db.



Configured JDBC URL, username, password as static final.



Implemented getConnection() with proper error handling.



Successfully tested connection to MySQL with a small TestDB class.



#### **Day 3 (18-09-25)**



**StudentDAO expanded:**



Added getStudentByEmail(String email) method.



Used try-with-resources for safe DB resource handling.



Maps all DB columns → Student object correctly.



**register.jsp created:**



Built a clean form with labels and proper input types (password hidden, dropdown for batch).



Form action points to registerServlet using POST.



**RegisterServlet built:**



Reads all request parameters from register.jsp.



Converts userYos (String) → int for yearOfStudy.



Populated Student POJO with setters.



Called StudentDAO.addStudent(student) to insert into DB.



Forwards to success.jsp or failed.jsp depending on result.





**End-to-End Test:**



Successfully submitted registration form.



Data inserted into students table in MySQL.



Redirected to success JSP.







#### **Day 4(19-09-25)**





###### **Student Login**



Created login.jsp with email + password form.



Implemented LoginServlet using StudentDAO.getStudentByEmail().



Fixed password check (.equals() instead of ==).



Added null-checks to avoid NullPointerException.



Successful login → session created, user forwarded to studentDashboard.jsp.



###### **Student Dashboard**



Built studentDashboard.jsp to greet student by name.



Used JSP EL ${currentStudent.name} instead of scriptlets → clean MVC.



###### **Logout Functionality**



Added logout link in dashboard header.



Created Logout servlet: invalidates session and redirects to login.jsp.



Verified that after logout, dashboard can’t be accessed without logging in again.





#### **Day 5 (20-09-25)**



###### **Teacher DAO**



Implemented addTeacher() to insert teacher details into DB.



Implemented getTeacherByEmail() to fetch teacher by email.



Added extractTeacher() helper for clean mapping from ResultSet.



Teacher Registration



Created teacherRegister.jsp form (name, email, phone, password, subject).



Implemented RegisterTeacherServlet to handle form submission.



Tested registration → data inserted successfully.



###### **Teacher Login**



Created teacherLogin.jsp form (email, password).



Implemented LoginTeacherServlet using DAO for validation.



On success → session created and forwarded to dashboard.



On failure → error message shown and returned to login.



###### **Teacher Dashboard**



Built teacherDashboard.jsp greeting with ${currentTeacher.name}.



Added logout link in header → redirects via existing Logout servlet.

