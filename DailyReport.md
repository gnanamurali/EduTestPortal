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





