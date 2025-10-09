 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${quizDetails.title}</title>
</head>
<body>

<h1>Start Quiz</h1>

<h2>${quizDetails.title}</h2>
<p><strong>Subject:</strong> ${quizDetails.subject}</p>
<p><strong>Created At:</strong> ${quizDetails.createdAt}</p>
<p>Time Limit: ${quizDetails.duration} minutes</p>

<div id="timer" style="font-size:20px; color:red;">${quizDetails.duration}:00</div>


<form action="submitQuiz" method="post">
  
  <input type="hidden" name="qid" value="${qid}"/>
  <input type="hidden" id="duration" value="${quizDetails.duration}">
  <input type="hidden" name="timeTaken" id="timeTaken">

  <c:forEach var="q" items="${quizQuestions}" varStatus="status">
    <div class="question" id="question${status.index}" style="display:none;">
      <p><strong>Q${status.index + 1}:</strong> ${q.questionText}</p>

     <label>
  	<input type="radio" name="answer_${q.queId}" value="A" onclick="saveAnswer(${q.queId}, 'A')">
  	${q.optionA}
	</label><br>

	<label>
  	<input type="radio" name="answer_${q.queId}" value="B" onclick="saveAnswer(${q.queId}, 'B')">
  	${q.optionB}
	</label><br>

	<label>
  	<input type="radio" name="answer_${q.queId}" value="C" onclick="saveAnswer(${q.queId}, 'C')">
  	${q.optionC}
	</label><br>

	<label>
  	<input type="radio" name="answer_${q.queId}" value="D" onclick="saveAnswer(${q.queId}, 'D')">
  	${q.optionD}
	</label><br>
    </div>
  </c:forEach>

 
  <div>
  <button type="button" id="prevBtn" onclick="showQuestion(currentIndex - 1)">Previous</button>
  <button type="button" id="nextBtn" onclick="showQuestion(currentIndex + 1)">Next</button>
  <button type="submit" id="submitBtn" style="display:none;">Submit Quiz</button>
</div>
</form>

<script src="quiz.js" ></script>
</body>
</html>