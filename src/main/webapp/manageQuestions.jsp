<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Questions</title>
</head>
<body>
<h1>Manage Questions for Quiz: ${quiz.title}</h1>
<%
    String msg = (String) session.getAttribute("message");
    if (msg != null) {
	%>
    <p><%= msg %></p>
	<%
        session.removeAttribute("message");
    }
%>
<c:if test="${empty questionList}">
  <p>No questions found. Click 'Add New Question' to create one.</p>
</c:if>

<a href="addQuestion?qid=${qid}">Add new question</a>
<c:forEach var="q" items="${questionList}">
  <div class="question-card">
     Q${q.queId}: ${q.questionText}
     A) ${q.optionA}
     B) ${q.optionB}
     C) ${q.optionC}
     D) ${q.optionD}
     <p><em>Correct Answer: ${q.correctOption}</em></p>
     <p>
     <a href="editQuestion?quesId=${q.queId}&qid=${qid}">[Edit]</a>
     <a href="deleteQuestion?quesId=${q.queId}&qid=${qid}"onclick="return confirm('Are you sure you want to delete this question?')">[Delete]</a>
  </div>
</c:forEach>
</body>
</html>