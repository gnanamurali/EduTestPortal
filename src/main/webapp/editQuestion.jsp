<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Question</title>
</head>
<body>
    <h1>Edit Question for Quiz: ${quiz.title}</h1>

    <form action="editQuestion" method="post">
        
        <input type="hidden" name="qid" value="${qid}">
        <input type="hidden" name="quesId" value="${question.queId}">

        
        <label for="questionText">Question:</label><br>
        <textarea id="questionText" name="questionText" rows="3" cols="50" required>
        ${question.questionText}</textarea><br><br>

        
        <label>Options:</label><br>
        A) <input type="text" name="optionA" value="${question.optionA}" required><br>
        B) <input type="text" name="optionB" value="${question.optionB}" required><br>
        C) <input type="text" name="optionC" value="${question.optionC}" required><br>
        D) <input type="text" name="optionD" value="${question.optionD}" required><br><br>

        
        <label for="correctOption">Correct Answer:</label><br>
        <select id="correctOption" name="correctOption" required>
            <option value="A" ${question.correctOption == 'A' ? 'selected' : ''}>A</option>
            <option value="B" ${question.correctOption == 'B' ? 'selected' : ''}>B</option>
            <option value="C" ${question.correctOption == 'C' ? 'selected' : ''}>C</option>
            <option value="D" ${question.correctOption == 'D' ? 'selected' : ''}>D</option>
        </select><br><br>

        <input type="submit" value="Update Question">
    </form>
</body>
</html>
