<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Question | ${quiz.title}</title>
<link rel="stylesheet" href="CSS/formPages.css">
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1 class="page-title">Edit Question for Quiz: ${quiz.title}</h1>

      <form action="editQuestion" method="post" class="styled-form">
        <input type="hidden" name="qid" value="${qid}">
        <input type="hidden" name="quesId" value="${question.queId}">

        <label for="questionText">Question:</label>
        <textarea id="questionText" name="questionText" rows="3" required>${question.questionText}</textarea>

        <label>Options:</label>
        <div class="options-grid">
          <div><strong>A)</strong> <input type="text" name="optionA" value="${question.optionA}" required></div>
          <div><strong>B)</strong> <input type="text" name="optionB" value="${question.optionB}" required></div>
          <div><strong>C)</strong> <input type="text" name="optionC" value="${question.optionC}" required></div>
          <div><strong>D)</strong> <input type="text" name="optionD" value="${question.optionD}" required></div>
        </div>

        <label for="correctOption">Correct Answer:</label>
        <select id="correctOption" name="correctOption" required>
          <option value="A" ${question.correctOption == 'A' ? 'selected' : ''}>A</option>
          <option value="B" ${question.correctOption == 'B' ? 'selected' : ''}>B</option>
          <option value="C" ${question.correctOption == 'C' ? 'selected' : ''}>C</option>
          <option value="D" ${question.correctOption == 'D' ? 'selected' : ''}>D</option>
        </select>

        <div class="btn-container">
          <button type="submit" class="primary-btn">Update Question</button>
          <a href="manageQuestions?qid=${qid}" class="back-btn">← Back to Manage Questions</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
