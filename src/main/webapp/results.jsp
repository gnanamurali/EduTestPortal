<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
int score = (Integer) request.getAttribute("quizScore");
int total = (Integer) request.getAttribute("totalQuestions");
double percentage = ((double) score / total) * 100;
%>
<%
String message;
if (percentage >= 90) message = "Excellent! 💪";
else if (percentage >= 75) message = "Great job!";
else if (percentage >= 50) message = "Good effort, keep improving!";
else message = "Don't worry, you'll do better next time!";
%>
<html>
<head>
<meta charset="UTF-8">
<title>Your Result</title>
</head>
<body>

    <h1>${quizTitle}</h1>
    <h2>${quizSubject}</h2>
    <br><br>
    <h3>You scored ${quizScore} out of ${totalQuestions}</h3>
    <p><strong>That is <%= String.format("%.2f", percentage) %>%</strong></p>
    <p><%= message %></p>
    
    <br><a href="availableQuizzes">Try Another Quiz</a>
    <a href="studentDashboard.jsp">Back to Dashboard</a>

</body>
</html>