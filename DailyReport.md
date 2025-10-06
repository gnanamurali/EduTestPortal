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





### **Day 6 (22-09-25)**





###### **Teacher Module – Create Quiz:**



Built createQuiz.jsp with session check, quiz title, subject (auto-filled), and batch checkboxes (B1, B2, B3).



###### **Implemented CreateQuizServlet with full flow:**



Reads quiz data and batches from form.



Validates teacher session.



Inserts quiz into QUIZZES using QuizDAO.addQuiz().



Assigns batches using QuizDAO.assignQuizToBatch().



Handles success, failure, and no-batch scenarios separately.



###### **Verified all conditions:**



Quiz created + batches assigned → success.



Quiz created + no batch selected → success.



Quiz created but batch assignment failed → error handled.



Quiz creation failed → error handled.



Debugged using an empty viewQuizzes.jsp as a landing page to confirm flow.



Confirmed rows inserted correctly in QUIZZES and QUIZ\_BATCHES.



### **Day 7 (23-09-25)**



###### **View Quizzes:**



Implemented QuizDAO.getQuizzesByTeacherId(tid) to fetch all quizzes created by a teacher.



Built ViewQuizzesServlet (GET) → validates session, fetches quizzes, forwards to viewQuizzes.jsp.



Created viewQuizzes.jsp with scriptlet loop to display quizzes in a table.



Added message handling to show success/failure feedback.



Integrated “View Your Quizzes” link into teacherDashboard.jsp.



###### **Delete Quiz:**



Added deleteQuiz(int qid) in QuizDAO with DB cascade support.



Built DeleteQuizServlet → validates teacher session, deletes quiz, sets success/error message, redirects to View Quizzes.



Updated viewQuizzes.jsp with Delete links for each quiz row.



Added JavaScript confirmation popup before delete to prevent accidental removals.



###### **Debugging \& Fixes:**



Fixed forward() vs redirect() flow by switching to PRG (Post/Redirect/Get).



Unified messages into a single message attribute instead of juggling message1.



Corrected table structure (added <tr>, aligned <th> and <td>).





### **Day 8 (24-09-2025)**



###### **Quiz Management (Teacher):**



Added updateQuiz(Quiz quiz) method in QuizDAO.



Built EditQuizServlet (GET) to load quiz details into edit form.



Created editQuiz.jsp with pre-filled form for title \& subject.



Built UpdateQuizServlet (POST) to update quiz and redirect using PRG (Post/Redirect/Get).



Successfully completed full CRUD for quizzes (Create, Read, Update, Delete).



Question Management (Teacher) – DAO Layer



###### **Started work on QuestionDAO.**

###### 

###### **Implemented:**



addQuestion(Question question) → inserts new question into DB.



getQuestionsByQuizId(int qid) → fetches all questions for a quiz.



deleteQuestion(int quesId) → removes a question from DB.



Confirmed DAO structure and prepared for integration with JSP/Servlets

