<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${quizDetails.title}</title>
<link rel="stylesheet" href="CSS/startQuiz.css">
</head>
<body>
    <jsp:include page="/includes/header.jsp" />

    <div class="quiz-container">
        <h1 class="quiz-title">${quizDetails.title}</h1>

        <div class="quiz-info">
            <p><strong>Subject:</strong> ${quizDetails.subject}</p>
            <p><strong>Created At:</strong> ${quizDetails.createdAt}</p>
            <p><strong>Time Limit:</strong> ${quizDetails.duration} minutes</p>
        </div>

        <div id="timer" class="quiz-timer">${quizDetails.duration}:00</div>

        <form action="submitQuiz" method="post" id="quizForm">
            <input type="hidden" name="qid" value="${qid}"/>
            <input type="hidden" id="duration" value="${quizDetails.duration}">
            <input type="hidden" name="timeTaken" id="timeTaken">

            <c:forEach var="q" items="${quizQuestions}" varStatus="status">
                <div class="question" id="question${status.index}" style="display:none;">
                    <p class="question-text"><strong>Q${status.index + 1}:</strong> ${q.questionText}</p>

                    <label><input type="radio" name="answer_${q.queId}" value="A" onclick="saveAnswer(${q.queId}, 'A')"> ${q.optionA}</label><br>
                    <label><input type="radio" name="answer_${q.queId}" value="B" onclick="saveAnswer(${q.queId}, 'B')"> ${q.optionB}</label><br>
                    <label><input type="radio" name="answer_${q.queId}" value="C" onclick="saveAnswer(${q.queId}, 'C')"> ${q.optionC}</label><br>
                    <label><input type="radio" name="answer_${q.queId}" value="D" onclick="saveAnswer(${q.queId}, 'D')"> ${q.optionD}</label><br>
                </div>
            </c:forEach>

            <div class="quiz-buttons">
                <button type="button" id="prevBtn" class="nav-btn">Previous</button>
                <button type="button" id="nextBtn" class="nav-btn">Next</button>
                <button type="submit" id="submitBtn" class="submit-btn" style="display:none;">Submit Quiz</button>
            </div>
        </form>
    </div>

    <script src="quiz.js"></script>
</body>
</html>
