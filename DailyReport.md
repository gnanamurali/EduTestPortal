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





### **Day 9 (3-10-2025)**



###### **Question Management (Teacher) – Full Integration**



Integrated Add Question, Manage Questions, Edit Question, and Delete Question functionalities.



Implemented AddQuestionServlet (GET + POST):



GET → verifies teacher authorization and forwards to addQuestion.jsp.



POST → adds question to DB using QuestionsDAO.addQuestion() and redirects back with success message.



Built addQuestion.jsp form with fields for question text, options A–D, and correct answer (dropdown).



Implemented ManageQuestionsServlet to fetch all questions of a quiz and display them in manageQuestions.jsp.



Added links for Edit and Delete beside each question; integrated confirmation dialog before deletion.



Added message boxes across JSPs (plain text, no JSTL) to display operation feedback.



Verified PRG (Post-Redirect-Get) flow and tested session authorization for teachers.



###### **Delete \& Edit Question**



Created DeleteQuestionServlet to remove questions securely after ownership validation.



Created EditQuestionServlet (GET + POST) to load existing question data, allow updates, and save changes.



Built editQuestion.jsp page pre-populating existing details.



All question operations tested end-to-end with proper console logs and validation messages.



### **Day 10 (07-10-2025)**



###### **Quiz Attempt (Student Module)**



Implemented AvailableQuizzesServlet → fetches quizzes assigned to student’s batch using QuizDAO.getQuizzesByBatch(batch).



Created availableQuizzes.jsp to list quizzes in <ul> format with “Start Quiz” link for each quiz.



Implemented StartQuizServlet (GET) → fetches quiz details + questions and forwards to startQuiz.jsp.



Built startQuiz.jsp with:



Quiz details (header).



Dynamic question display loop using JSTL.



Radio buttons for options A–D.



“Previous / Next” buttons controlled via JavaScript and timer-based auto-submit.



###### **Quiz Submission \& Results**



Implemented SubmitQuizServlet (POST):



Calculates score by matching answers with correct options.



Prevents re-attempt via ResultsDAO.hasAttemptedQuiz() check.



Saves result using ResultsDAO.saveResult() and forwards to results.jsp.



Designed results.jsp:



Displays quiz title, subject, score and percentage.



Generates a performance-based message (e.g., “Excellent”, “Good effort”).



Added links to retake another quiz or return to dashboard.



Added message boxes to availableQuizzes.jsp for attempt status (success/failure).



Thoroughly tested end-to-end quiz attempt and score storage flow in MySQL results table.

#### 

#### **Day 11 (08-10-2025)**



###### **Results \& Analytics – Student Side**



Implemented ResultsDAO.getResultsByStudentId(int sid):



Performs a JOIN between RESULTS and QUIZZES to fetch quiz title, subject, and score together.



Returns a list of Result objects containing full quiz details.



###### **Created ViewResultServlet:**



Validates student session.



Fetches all results for the logged-in student.



Forwards to viewResults.jsp with name, batch, and quiz details.



###### **Designed viewResults.jsp:**



Displays results in a table format with columns: Quiz Title, Subject, Score, and Date.



Shows “No results found” if the student hasn’t taken any quizzes yet.



Added navigation links back to dashboard.



###### **Bug Fixes:**



Fixed ClassCastException in results.jsp by properly handling double conversion for percentage.



Cleaned up redundant session attributes and improved console logging.



#### **Day 12 (09-10-2025)**



##### **Teacher Result Management**

###### 

###### **Created TeacherViewResultServlet:**



Fetches all quizzes created by the logged-in teacher using QuizDAO.getQuizzesByTeacherId().



Displays them in teacherViewResults.jsp table with “View Results” action link per quiz.



###### **Implemented QuizResultsServlet:**



Fetches all student results for a specific quiz using a new DAO method with JOIN between RESULTS, STUDENTS, and QUIZZES.



Forwards data to quizResults.jsp for display.

###### 

###### **Designed teacherViewResults.jsp:**



Table showing all quizzes (ID, title, subject, created date).



Added "View Results" action for each quiz row.



###### **Designed quizResults.jsp:**



Table showing student-wise results (Student ID, Name, Batch, Score, Taken At).



Displays “No results yet” if no students have taken the quiz.



Added navigation link back to teacher dashboard.



All result-related functionalities (student + teacher) were tested end-to-end.



#### **Day 13 (10-10-2025)**



###### **Timer + Session Persistence (Quiz Page)**



Fully implemented quiz timer with session-based persistence and AJAX.



**Core Features:**



Timer duration fetched from QUIZZES table.



Timer continues after refresh (remaining time stored in session).



Answers saved automatically via AJAX to SaveAnswerServlet.



Timer + answers restored via FetchQuizStateServlet.



Auto-submit triggers when time expires.



###### **Frontend (startQuiz.jsp + quiz.js):**



Added visible countdown timer.



Added auto-save and progress recovery logic.



Answers preserved even after refresh.



Toast feedback for “Answer Saved” shown dynamically.



###### **Backend (Servlets):**



StartQuizServlet: Initializes quiz session state.



SaveAnswerServlet: Updates answers + remaining time in session.



FetchQuizStateServlet: Returns quiz state JSON for page reload.



SubmitQuizServlet: Cleans up session after quiz submission.



###### **Security Enhancement**



Integrated BCrypt for password hashing and verification:



Added PasswordUtil.java with hash() and verify() methods.



Updated registration servlets (Student + Teacher) to hash passwords before DB insert.



Updated login servlets to use secure verification.



Added required JARs (bcrypt-0.10.2.jar, bytes-1.5.0.jar) to WEB-INF/lib.



Tested successful login and registration using bcrypt-hashed passwords.





#### **Day 14 (11-10-2025)**



##### **Admin Module – Setup \& Authentication**

###### 

###### **Created AdminDAO with core methods:**



addAdmin(Admin admin) — inserts admin credentials using bcrypt hashing.



getAdminByEmail(String email) — retrieves admin details for login.



Built AdminRegisterServlet and adminRegister.jsp for secure registration.



Implemented AdminLoginServlet with bcrypt verification.



###### Added AdminDashboardServlet and adminDashboard.jsp:



Displays summary stats — total students, teachers, and quizzes.



Links to “Manage Students,” “Manage Teachers,” and “Manage Quizzes.”



Added proper session handling and redirection to prevent direct dashboard access without login.



#### **Day 14 (11-10-2025)**

##### Admin Module – Setup \& Authentication



###### Created AdminDAO with core methods:



addAdmin(Admin admin) — inserts admin credentials using bcrypt hashing.



getAdminByEmail(String email) — retrieves admin details for login.



Built AdminRegisterServlet and adminRegister.jsp for secure registration.



Implemented AdminLoginServlet with bcrypt verification.



###### **Added AdminDashboardServlet and adminDashboard.jsp:**



Displays summary stats — total students, teachers, and quizzes.



Links to “Manage Students,” “Manage Teachers,” and “Manage Quizzes.”



Added proper session handling and redirection to prevent direct dashboard access without login.





#### Day 15 (12-10-2025)

##### Admin – Manage Students



Implemented ManageStudentsServlet to fetch and display all student details.



###### Created manageStudents.jsp with search/filter by batch and actions:



Edit, Delete, and View Results for each student.



Added success/error message display using JSTL.



Implemented delete functionality with confirmation dialogs.



Ensured that deleting a student also cascades results removal in DB.



##### Admin – Manage Teachers



Created ManageTeachersServlet + manageTeachers.jsp.



Displays all teachers with options to edit, delete, or view created quizzes.



Ensured full authorization control so only admins can modify teacher data.





#### **Day 16 (13-10-2025)**

##### **Admin – Manage Quizzes**



Created ManageQuizzes.jsp for viewing and managing all quizzes.



###### **Added visibility toggle system:**



Created ToggleQuizVisibilityServlet shared between Teacher \& Admin.



Updates IS\_VISIBLE in QUIZZES table.



Redirects dynamically based on user role (admin or teacher).



Integrated delete functionality using shared DeleteQuizServlet.



Added link to “View Results” for any quiz directly from admin panel.



#### 

#### **Day 17 (14-10-2025)**

##### **Shared Results System**



Enhanced QuizResultsServlet to support both Admin and Teacher roles.



Added userRole attribute to differentiate behavior in JSP.



Updated quizResults.jsp to dynamically show correct navigation options:



Admin → Back to Manage Quizzes / Dashboard



Teacher → Back to View Quizzes / Dashboard



Unified DAO logic for fetching quiz results accessible to both roles.





##### **Day 18 (15-10-2025)**

##### **Admin Dashboard Enhancements**



###### **Refined adminDashboard.jsp layout:**



Clean summary cards showing Students, Teachers, Quizzes, and Active Quizzes.



Added quick navigation buttons for key management pages.



Integrated consistent message display using shared header and message boxes.



Added access control to prevent teacher/student dashboard access by admins.



#### **Day 19 (16-10-2025)**

##### **Quiz Visibility \& Filtering Improvements**



Teachers can now mark their quizzes as “hidden” while still editable.



Students only see visible quizzes in AvailableQuizzesServlet via SQL filter:



SELECT \* FROM QUIZZES WHERE IS\_VISIBLE = 1



Confirmed that hidden quizzes are excluded from student listings but remain viewable by teacher/admin.



#### **Day 20 (17-10-2025)**

###### **Global Design Overhaul**



Introduced layout.css and header.jsp for unified design structure.



Added sticky navbar with logo (edutestbackground1.png) and purple theme (#6c63ff).



Defined container and dashboard sections with subtle shadows and rounded corners.



All dashboards (student, teacher, admin) now share consistent layout via header.jsp.



#### **Day 21 (18-10-2025)**

###### **Background Integration \& Visual Effects**



Set global background using bgimg1.png through layout.css:



Added linear-gradient overlay for better readability.



Enabled parallax scroll effect using background-attachment: fixed.



Implemented glassy effect for dashboard containers using:



background: rgba(255,255,255,0.9);

backdrop-filter: blur(6px);



#### 

#### **Day 22 (19-10-2025)**

###### **Table and Form Styling**



Added tablePages.css and formPages.css.



Styled all table-based pages (manage\*, results\*, availableQuizzes.jsp) with:



Hover highlights, alternating rows, and action buttons.



Styled all forms with centered card layouts and shadowed inputs.



Unified color palette:



Primary: #6c63ff, Accent: #5a4fff, Success: #43a047, Danger: #ff4d4d.



Ensured full responsiveness for mobile devices.



#### 

#### **Day 23 (20-10-2025)**

###### **Profile System (Student)**



Added “Profile” option in student dashboard.



Created studentProfile.jsp:



Displays full student details.



Includes Edit and Delete buttons at the bottom.



Linked edit/delete with existing StudentDAO and servlet flows.



Ensured session sync so updated info reflects immediately on dashboard.



#### 

#### **Day 24 (21-10-2025)**

###### **Home Page (index.jsp) Design**



Designed a proper homepage introducing EduTestPortal.



Added main CTA buttons for “Student Register” and “Login”.



Teacher login link placed in footer for professional layout.



Header + footer styled consistently with theme.



Included short description about the portal and its purpose.



#### **Day 25 (24-10-2025)**

###### **Final Integration \& Testing**



Verified background consistency across all pages via header.jsp inclusion.



Completed functional and UI testing for all modules (Student, Teacher, Admin).



Fixed session timeout redirect issues and ensured logout clears all data.



Final polish: adjusted spacing, text contrast, and responsive behavior.



EduTestPortal is now feature-complete, secure, and visually consistent.

