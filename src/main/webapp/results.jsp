<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
double percentage = Double.parseDouble((String) request.getAttribute("percentage"));

String message;
String color;

if (percentage >= 90) {
    message = "Excellent! 💪";
    color = "#28a745"; // green
} else if (percentage >= 75) {
    message = "Great job!";
    color = "#007bff"; // blue
} else if (percentage >= 50) {
    message = "Good effort, keep improving!";
    color = "#ffc107"; // yellow
} else {
    message = "Don't worry, you'll do better next time!";
    color = "#dc3545"; // red
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Result</title>
<link rel="stylesheet" href="CSS/results.css">
</head>
<body>
    <jsp:include page="/includes/header.jsp" />

    <div class="result-container">
        <h1 class="quiz-title"><%= request.getAttribute("quizTitle") %></h1>
        <h2 class="quiz-subject"><%= request.getAttribute("quizSubject") %></h2>

        <div class="score-card">
            <h3>You scored <%= request.getAttribute("quizScore") %> 
                out of <%= request.getAttribute("totalQuestions") %></h3>
            <p class="percentage" style="color:<%= color %>;">
                <strong><%= String.format("%.2f", percentage) %>%</strong>
            </p>
            <p class="message"><%= message %></p>
        </div>

        <div class="btn-group">
            <a href="availableQuizzes" class="btn primary-btn">Try Another Quiz</a>
            <a href="studentDashboard.jsp" class="btn secondary-btn">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
