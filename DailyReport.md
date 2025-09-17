# 		   **DAILY REPORT**				



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

