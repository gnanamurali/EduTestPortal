<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Question | ${quiz.title}</title>
<link rel="stylesheet" href="CSS/formPages.css">
</head>

<body>
  <jsp:include page="/includes/header.jsp" />

  <div class="page-container">
    <div class="content-card">
      <h1 class="page-title">Add Question for Quiz: ${quiz.title}</h1>

      <% 
        String msg = (String) session.getAttribute("message");
        if (msg != null) { 
      %>
        <p class="success-msg"><%= msg %></p>
      <% 
          session.removeAttribute("message");
        } 
      %>

      <form action="addQuestion" method="post" class="styled-form">
        <input type="hidden" name="qid" value="${qid}"/>

        <label for="questionText">Question:</label>
        <textarea id="questionText" name="questionText" rows="3" required></textarea>

        <label>Options:</label>
        <div class="options-grid">
          <div><strong>A)</strong> <input type="text" name="optionA" required></div>
          <div><strong>B)</strong> <input type="text" name="optionB" required></div>
          <div><strong>C)</strong> <input type="text" name="optionC" required></div>
          <div><strong>D)</strong> <input type="text" name="optionD" required></div>
        </div>

        <label for="correctOption">Correct Answer:</label>
        <select id="correctOption" name="correctOption" required>
          <option value="A">A</option>
          <option value="B">B</option>
          <option value="C">C</option>
          <option value="D">D</option>
        </select>

        <div class="btn-container">
          <button type="submit" class="primary-btn">Save Question</button>
          <a href="manageQuestions?qid=${qid}" class="back-btn">← Back to Manage Questions</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
