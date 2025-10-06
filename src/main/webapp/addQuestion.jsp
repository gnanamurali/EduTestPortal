<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Question</title>
</head>
<body>
	<h1>Add Questions for Quiz: ${quiz.title}</h1>
	<%
    String msg = (String) session.getAttribute("message");
    if (msg != null) {
	%>
    <p><%= msg %></p>
	<%
        session.removeAttribute("message");
    }
	%>
	<form action="addQuestion" method="post">
		 <input type="hidden" name="qid" value="${qid}"/><br>
		 
		 <label for="questionText">Question:</label><br>
         <textarea id="questionText" name="questionText" rows="3" cols="50" required></textarea><br><br>
          
         <label>Options:</label><br>
    		 A) <input type="text" name="optionA" required><br>
   		 B) <input type="text" name="optionB" required><br>
    		 C) <input type="text" name="optionC" required><br>
    		 D) <input type="text" name="optionD" required><br><br>
    		 
    		 
    		<label for="correctOption">Correct Answer:</label><br>
        <select id="correctOption" name="correctOption" required>
        <option value="A">A</option>
        <option value="B">B</option>
        <option value="C">C</option>
        <option value="D">D</option>
        </select><br><br>
        
        <input type="submit" value="Save Question">
        
    </form>
		 

</body>
</html>